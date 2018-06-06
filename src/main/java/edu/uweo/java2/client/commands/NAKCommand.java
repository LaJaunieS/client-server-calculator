package edu.uweo.java2.client.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.ClientServerTester;

/**Encapsulates a condition where this client server sends to the server
 * an Object that is not an AbstractCommand object, triggering a "NAK" 
 * response from the server
 * @author slajaunie
 *
 */
public class NAKCommand implements Serializable {
    /*
     * Technically, this is not a command; it is an object returned 
     * by the server in the case that the client sends an object 
     * that is not an AbstractCommand.;
     */
    private Receiver receiver;
    private int port;
    private static final long serialVersionUID = -8964896791639024148L;
    private static Logger log = LoggerFactory.getLogger("NAKCommand.class");
    /**Constructor which will simulate a client server sending a 
     * non-AbstractCommand object to the server, which will trigger a "NAK" response
     * @param port
     */
    public NAKCommand(int port) {
        this.port = port;
    }
    
    /**Executes this command, by invoking the Receiver's <code>action</code>
     * method, passing this command as an argument
     * @see edu.uweo.java2.assignment8.AbstractCommand#execute()
     */
    public String execute(DummySerializableObject obj) {
        /*Get the IP address for this local machine*/
        InetAddress addr = InetAddress.getLoopbackAddress();
        
        /*will contain response back from server*/
        String serverResponse=null;
        /*open socket to connect with server*/
        log.info("Making client request at port {}",this.port);
        
        //move some/most of this to separate methods
        try (   Socket socket = new Socket(addr,this.port);
                /*Input stream for ack from server*/
                InputStream iStream = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader( iStream );
                BufferedReader bReader = new BufferedReader( reader );
                
                OutputStream oStream = socket.getOutputStream();
                /*For passing command object to server*/
                ObjectOutputStream writer = new ObjectOutputStream( oStream );
                ) {
            
            /*write command object to send to server*/
            writer.writeObject(obj);
            /*get ack/nak string from server*/
            serverResponse = bReader.readLine();
            log.info("{}; Invalid ACK from server");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        log.info("Connection with server at port {} closed",port);
        return serverResponse;
    }
    
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    private Receiver getReceiver() {
        return this.receiver;
    }
    
    public class DummySerializableObject implements Serializable{}       
}
