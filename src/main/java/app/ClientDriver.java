package app;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.uweo.java2.client.Client;
import edu.uweo.java2.client.commands.AbstractCommand;
import edu.uweo.java2.client.commands.AddCommand;
import edu.uweo.java2.client.commands.DivCommand;
import edu.uweo.java2.client.commands.MulCommand;
import edu.uweo.java2.client.commands.NAKCommand;
import edu.uweo.java2.client.commands.ShutdownCommand;
import edu.uweo.java2.client.commands.SubCommand;

public class ClientDriver {
    /*This application will spawn five threads. 
     * Each thread will use the Client class to send four commands to the server:*/
    /*The main thread will wait for the child threads to complete, then use the 
     * Client class to send a ShutdownCommand to the server.*/
    public static void main(String[] args) {
        boolean open = true;
        
        Client client = new Client(4885);
        NAKCommand nakCommand =  new NAKCommand(4885);
        
        AddCommand addCommand = new AddCommand( BigDecimal.valueOf(0.5),
                                                BigDecimal.valueOf(-0.7), 
                                                250, 1000 );
        DivCommand divCommand = new DivCommand( BigDecimal.valueOf(0.9),
                                                BigDecimal.valueOf(3.0), 
                                                250 , 500 );
        MulCommand mulCommand = new MulCommand( BigDecimal.valueOf(0.9), 
                                                BigDecimal.valueOf(2.0), 
                                                100, 600 );  
        SubCommand subCommand = new SubCommand( BigDecimal.valueOf(0.5), 
                                                BigDecimal.valueOf(-0.7), 
                                                125, 500 );
        ShutdownCommand sdCommand = new ShutdownCommand();
        
        
        List<BigDecimal> responseList = new ArrayList<BigDecimal>();
        
        while (open) {
            try {
                responseList.add(client.execute(subCommand));
                responseList.add(client.execute(addCommand));
                responseList.add(client.execute(divCommand));
                responseList.add(client.execute(mulCommand));
                //responseList.add(nakCommand.execute(nakCommand.new DummySerializableObject()));
                responseList.stream().forEach(System.out::println);
                System.out.println(client.execute(sdCommand));
            } catch (IOException e) {
                e.printStackTrace();
            }
            open = false;
            
        }
    }
    
}
