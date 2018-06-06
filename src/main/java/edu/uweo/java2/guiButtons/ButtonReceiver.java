package edu.uweo.java2.guiButtons;

import java.io.IOException;

import edu.uweo.java2.client.Client;
import edu.uweo.java2.client.commands.AbstractCommand;
import edu.uweo.java2.client.commands.AddCommand;
import edu.uweo.java2.client.commands.DivCommand;
import edu.uweo.java2.client.commands.MulCommand;
import edu.uweo.java2.client.commands.SubCommand;

public class ButtonReceiver {
    public String action(AddButton button, Double value1, Double value2) {
        AddCommand cmd = new AddCommand(value1,value2,100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    public String action(SubButton button, Double value1, Double value2) {
        SubCommand cmd = new SubCommand(value1, value2, 100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    public String action(MulButton button, Double value1, Double value2) {
        MulCommand cmd = new MulCommand(value1, value2, 100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    public String action(DivButton button, Double value1, Double value2) {
        DivCommand cmd = new DivCommand(value1, value2, 100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    public String execute(AbstractCommand cmd) {
        Client client = new Client(4885);
        String serverResponse = null;
        try {
            serverResponse = client.execute(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
}
