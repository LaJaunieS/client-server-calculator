package edu.uweo.java2.assignment9.server;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import app.ClientServerTester;
import edu.uweo.java2.server.Server;
import edu.uweo.java2.server.Server.Receiver;

public class ServerTest {

    ClientServerTester cst; 
    
    /*Before tests, obtain cst object persisted to file*/
    @Before public void initialize() {
        final Path FILE_PATH = Paths.get("src","main","resources","server.log");
        final File LOG_FILE= new File(FILE_PATH.toString());
        try (
        FileInputStream fiStream = new FileInputStream(LOG_FILE);
        ObjectInputStream iStream = new ObjectInputStream(fiStream);
                ){
                cst = (ClientServerTester) iStream.readObject();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testServer() {
        Server server = new Server(4885);
        assertTrue(server instanceof Server);
    }

    @Test
    public void testExecute() {
    /*NOTE- at least one client/server exchange
     * must have taken place for this test to pass
     */
        assertTrue(cst.serverSocketOpened);
        assertTrue(cst.serverSocketClosed);
        assertTrue(cst.threadPoolSuccessfullyShutdown);
    }

    @Test
    public void testExecuteServerSocket() {
        assertTrue(cst.serverListening);
        assertTrue(cst.clientConnectionAcceptedByServer);        
    }

    @Test
    public void testProcessRequestObject() {
        assertTrue(cst.commandObjAcceptedAndResponseSentToClient);
    }

}
