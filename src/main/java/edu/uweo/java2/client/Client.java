package edu.uweo.java2.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.ClientServerTester;
import edu.uweo.java2.client.commands.AbstractCommand;

/**Encapsulates a client which makes a request to a Server. Writes an instance of 
 * an <code>AbstractCommand</code> subclass to an <code>ObjectOutputStream</code>
 * for sending to the server. Reads a server's <code>String</code> response after
 * receiving the instance of <code>AbstractCommand</code>'s subclass.
 * @author slajaunie
 *
 */
public class Client {
    /**The port the request will be directed towards*/
    private int port;
    
    private static final Logger log = LoggerFactory.getLogger(Client.class);
    /**Tester object*/
    public ClientServerTester cst = new ClientServerTester();
    
    /**Constrcuts a new instance of <code>Client</code> setting its port to the 
     * given port
     * @param port the port the request will be directed towards
     */
    public Client(int port) {
        this.port = port;
    }
    
    /**Forwards a given command to the server for execution, by: 
     * <ul>
     * <li>Opening a connection to the server</li>
     * <li>Instantiating a command object</li>
     * <li>Passing the command object to the server via 
     * <code>ObjectOutputStream</code></li>
     * <li>Waits for an acknowledgement from the server</li>
     * <li>Reads the acknowledgement as an <code>InputStream</code></li>
     * <li>Closes the connection</li></ul>
     * @param command a given instance or subclass of <code>AbstractCommand</code>
     * @return a <code>String</code> that is the acknowledgement sent from the server
     * @throws IOException if there was an error connecting to the server
     */
    public BigDecimal execute(AbstractCommand command) throws IOException {
        
        /*Get the IP address for this local machine*/
        InetAddress addr = InetAddress.getLoopbackAddress();
        
        /*will contain response/command result back from server*/
        BigDecimal serverResponse=null;
        /*open socket to connect with server*/
        log.info("Making client request at port {}",this.port);
        
        try (   Socket socket = new Socket(addr,this.port);
                /*Note, OutputStream MUST come before InputStreams,
                 * otherwise will block*/
                OutputStream oStream = socket.getOutputStream();
                /*For passing command object to server*/
                ObjectOutputStream ooStream = new ObjectOutputStream( oStream );
                
                /*Input stream for executed command from server*/
                InputStream iStream = socket.getInputStream();
                ObjectInputStream oiStream = new ObjectInputStream( iStream );
                ) {
            
            cst.commandObjWrittenToOutputStream = true;
            /*write command object to send to server*/
            ooStream.writeObject(command);
            /*get returned/executed command result from server*/
//            if (!(oiStream.readObject() instanceof AbstractCommand))
//                log.info("Object returned from server is not an instance of AbstractCommand");
//            else 
                
                serverResponse = (BigDecimal) oiStream.readObject();
                if (serverResponse.toString().equalsIgnoreCase("NAK")) {
                    log.info("Invalid ACK from server");
                } else {
                    cst.ServerAckReadByClient = true;
                    log.info("request from server acknowledged");
                }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        log.info("Connection with server at port {} closed",port);
        writeLogTestToFile();
        return serverResponse;
    }
    
    /**Persist the <code>ClientServerTester</code> object to a set file, 
     * for reading later in a unit test
     * 
     */
    private void writeLogTestToFile() {
        final Path FILE_PATH = Paths.get("src","main","resources","client.log");
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
    
}
