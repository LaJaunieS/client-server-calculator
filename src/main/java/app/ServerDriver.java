package app;

import java.io.IOException;

import edu.uweo.java2.server.Server;

public class ServerDriver {
    public static void main(String[] args) {
        Server server = new Server(4885);
        System.out.println("Driver message: Server open");

        try {
            server.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Driver message: Server closed");
        
    }
    
}
