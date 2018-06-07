package edu.uweo.java2.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.ClientServerTester;
import edu.uweo.java2.client.commands.AbstractCommand;
import edu.uweo.java2.client.commands.NAKCommand;
import edu.uweo.java2.client.commands.ShutdownCommand;

/**Encapsulates a server which listens for and accepts request(s) from 
 * a client. Reads an instance or subclass of <code>AbstractCommand</code>
 * from an <code>ObjectOutputStream</code> and writes an acknowledgement in
 * response back to the client
 * @author slajaunie
 *
 */
public class Server {
    
    /**The receiver of the command instances*/
    private final Receiver receiver;
    /**The port on which this server will be listening for a client request*/
    private int port;
    
    private static final Logger log = LoggerFactory.getLogger(Server.class);
    
    /**Test for whether server port connection is active**/
    private boolean isActive = true;

    /**Tester object*/
    public ClientServerTester cst = new ClientServerTester();
    
    /**Constructs a new Server instance which will listen for
     * requests at the given port. Instantiates a new instance of 
     * <code>Receiver</code>
     * @param port port in which a Server will listen for requests
     */
    public Server(int port) {
        this.port = port;
        this.receiver = new Server.Receiver();
    }
    
    /**Opens the server's connection to being listening
     * for client requests, and then executes the listener
     * @throws IOException if there is an error opening the connection
     */
    public void execute() throws IOException{
        try (ServerSocket listener = new ServerSocket(port)){
            log.info("Server connection opened at port {}", port);
            cst.serverSocketOpened = true;
            execute(listener);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        cst.serverSocketClosed = true;
        writeLogTestToFile();
        log.info("Server connection at port {} has closed",port);
        
    }
    
    /**Starts listening for client connections
     * @param listener a <code>ServerSocket</code> by which this Server
     * will accept requests from a client, and upon accepting a request,
     * opens a new <code>Thread</code> instance for the request, 
     * and runs the method to process an object received, preserving as a
     * <code>Future</code> instance. Once all client connections are
     * accumulated, executes each <code>Future</code> instance 
     * @throws IOException if there is an error opening the listener
     * @throws ClassNotFoundException if the object returned by the 
     * client request is not recognized. Server will also return a String
     * reading "NAK" to the client making the request
     */
    public void execute( ServerSocket listener) 
            throws IOException, ClassNotFoundException {
        listener.setSoTimeout(15000);
        
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<AbstractCommand> future;
        List<Future<AbstractCommand>> futures = new ArrayList<>();

        while (isActive) {
            try {
                /*Submit process as a new thread for every client connection
                 * received by the listener and process the object sent
                 * from the client*/
                future = pool.submit(new Process(listener.accept()));
                log.info("Listening for client connections....");
                cst.serverListening = true;
                
                futures.add(future);
                /*If client request connected...*/
                cst.clientConnectionAcceptedByServer = true;
                log.info("Connection accepted");
                /*...execute futures/commands in order of receipt*/
                for (Future<AbstractCommand> f: futures) {
                    try {
                            f.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                }
            } catch (SocketTimeoutException ex) {
                log.info("Server connection at port {} timed out",port);
                isActive = false;
            }
        }
        /*Shutdown the pool after all threads have completed*/
        pool.shutdown();
        cst.threadPoolSuccessfullyShutdown = pool.isShutdown();
        listener.close();
    }
    
    /**Extends <code>edu.uweo.java2.assignment9.Receiver</code> to handle a 
     * <code>ShutdownCommand</code> instance. Additionally adds a method to  
     * pause execution in an existing thread to simulate a client/server environment
     * @author slajaunie
     * @see edu.uweo.java2.client.commands.Receiver
     */
    public class Receiver extends edu.uweo.java2.client.commands.Receiver implements Serializable{
     /*
      * The Receiver subclass will execute as it did in assignment 8 except:

    After executing the given command, it will sleep for a period randomly 
    selected in the range of the command's workMillisMin and workMillisMax, inclusive.
   
      */
        /**Executes a given <code>ShutdownCommand</code>, setting the server's internal
         * <code>isActive</code> field to false, causing the server the exit a listening
         * loop and shutdown
         * @param command an instance of <code>ShutdownCommand</code>
         */
        public void action(ShutdownCommand command) {
            log.info("Shutdown command detected; shutting down");
            isActive = false;
        }
        
        /**Pauses execution of an open thread for a random interval within 
         * the given min/max range
         * @param min a <code>double</code> that is the minimum bound of the random 
         * interval 
         * @param max a <code>double</code> that is the maximum bound of the random 
         * interval
         */
        public void pause(double min, double max) {
            /*make command values doubles, convert to long hereVV*/
            long ms = (long) (min + (Math.random()*(max-min)));
            try {
                Thread.sleep(ms);
                /*add cst field that tests that this pause was successful*/
                log.info("Thread paused for {} ms",ms);
            } catch (InterruptedException e) {
                log.warn("Thread pause failed");
                e.printStackTrace();
            }
        }
    }
    
    /**Encapsulates the processing of an <code>AbstractCommand</code> object
     * from a client.
     * @author slajaunie
     *
     */
    private class Process implements Callable<AbstractCommand> {
        private Socket client;
        private BigDecimal serverResponse; 
        
        /**Constructs a new instance with the given <code>Socket</code>
         * @param client a <code>Socket</code> representing a request from a
         * client
         */
        public Process(Socket client){
            this.client = client;
        }
        
        /**Processes an object received via a client request. Reads the object via
         * an <code>ObjectOutputStream</code> and, if recognized as an instance or
         * subclass of <code>AbstractCommand</code>, executes that command via this 
         * server's attached receiver. Sends an acknowledgement back to the client via 
         * an <code>OutputStream</code>.
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public AbstractCommand call() {
            AbstractCommand command = null;
            try (   /*Note, OutputStream MUST come before InputStreams,
                     * otherwise will block*/
                    OutputStream oStream = client.getOutputStream();
                    ObjectOutputStream ooStream = new ObjectOutputStream( oStream );
                    
                    InputStream iStream = client.getInputStream();
                    ObjectInputStream oiStream = new ObjectInputStream( iStream );
                    ){
                Object obj = oiStream.readObject();
                /*First confirm if returned obj is a command*/
                if (!(obj instanceof AbstractCommand)){
                        /*if command not recognized, write NAK string to output stream*/
                        cst.serverNAKReadByClient = true;
                        log.warn("Command not recognized");
                        ooStream.writeObject("Command not recognized");
                        throw new ClassNotFoundException("Command must be an instance of"
                                + " AbstractCommand");
                } else {
                    command = (AbstractCommand) obj;
                    /*execute the command*/
                    cst.commandObjAcceptedAndResponseSentToClient = true;
                    command.setReceiver(receiver);
                    if (command instanceof ShutdownCommand) {
                        /*If shutdown command, execute shutdown and write back a message*/
                        ooStream.writeObject("SHUTDOWN");
                        receiver.action((ShutdownCommand)command);
                    } else {
                        /*If everything checks out, execute the command and write result back to 
                         * the client*/
                        receiver.pause(command.getWorkMillisMin(),
                                command.getWorkMillisMax());
                        command.execute();
                        ooStream.writeObject(command.getResult());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return command;
        }
    }
     
    
     /**Persist the <code>ClientServerTester</code> object to a set file, 
      * for reading later in a unit test
      */
     private void writeLogTestToFile() {
        final Path FILE_PATH = Paths.get("src","main","resources","server.log");
        final File LOG_FILE= new File(FILE_PATH.toString());

        
        try (FileOutputStream f = new FileOutputStream(LOG_FILE);
                ObjectOutputStream oStream = new ObjectOutputStream(f);
                ){
            oStream.writeObject(cst);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**Gets the isActive state of this Server
     * @return a <code>boolean</code> that reflects the isActive state of this server
     */
    public boolean isActive() {
        return isActive;
    }

    /**Sets the isActive state of this Server
     * @param isActive <code>boolean</code> that is to be the new isActive
     * state of this Server
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
    

