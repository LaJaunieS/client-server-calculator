package edu.uweo.java2.assignment9.client;

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
import edu.uweo.java2.client.Client;
import edu.uweo.java2.client.commands.AddCommand;

public class ClientTest {

    ClientServerTester cst; 
    
    @Before public void initialize() {
        final Path FILE_PATH = Paths.get("src","main","resources","client.log");
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
    public void testClient() {
        Client client = new Client(4885);
        assertTrue(client instanceof Client);
    }

    @Test
    public void testExecute() {
        /*NOTE- at least one client/server exchange
         * must have taken place for this test to pass
         */
        assertEquals(true, cst.commandObjWrittenToOutputStream);
        assertEquals(true, cst.ServerAckReadByClient);
    }
}
