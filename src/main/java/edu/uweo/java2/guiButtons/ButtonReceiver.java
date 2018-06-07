package edu.uweo.java2.guiButtons;

import java.io.IOException;

import edu.uweo.java2.client.Client;
import edu.uweo.java2.client.commands.AbstractCommand;
import edu.uweo.java2.client.commands.AddCommand;
import edu.uweo.java2.client.commands.DivCommand;
import edu.uweo.java2.client.commands.MulCommand;
import edu.uweo.java2.client.commands.SubCommand;

public class ButtonReceiver {
    
    /**Instantiates an instance of <code>AddCommand</code>, passing in the 
     * give <code>Double</code> values, and then passing the <code>AddCommand</code>
     * to a new instance of <code>Client</code> for execution
     * @param button an instance of <code>AddButton</code>
     * @param value1 a <code>Double</code> that will correspond to the value of
     * the first JTextBox component
     * @param value2 a <code>Double</code> that will correspond to the value of
     * the second JTextBox component
     * @return the <code>String</code> Object returned by the Server instance in
     * response to the client request- will contain the result of the given AddCommand 
     */
    public String action(AddButton button, Double value1, Double value2) {
        AddCommand cmd = new AddCommand(value1,value2,100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    /**Instantiates an instance of <code>SubCommand</code>, passing in the 
     * give <code>Double</code> values, and then passing the <code>SubCommand</code>
     * to a new instance of <code>Client</code> for execution
     * @param button an instance of <code>SubButton</code>
     * @param value1 a <code>Double</code> that will correspond to the value of
     * the first JTextBox component
     * @param value2 a <code>Double</code> that will correspond to the value of
     * the second JTextBox component
     * @return the <code>String</code> Object returned by the Server instance in
     * response to the client request- will contain the result of the given SubCommand 
     */
    public String action(SubButton button, Double value1, Double value2) {
        SubCommand cmd = new SubCommand(value1, value2, 100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    /**Instantiates an instance of <code>MulCommand</code>, passing in the 
     * give <code>Double</code> values, and then passing the <code>MulCommand</code>
     * to a new instance of <code>Client</code> for execution
     * @param button an instance of <code>MulButton</code>
     * @param value1 a <code>Double</code> that will correspond to the value of
     * the first JTextBox component
     * @param value2 a <code>Double</code> that will correspond to the value of
     * the second JTextBox component
     * @return the <code>String</code> Object returned by the Server instance in
     * response to the client request- will contain the result of the given MulCommand 
     */
    public String action(MulButton button, Double value1, Double value2) {
        MulCommand cmd = new MulCommand(value1, value2, 100,100);
        String serverResponse=null;
        serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    /**Instantiates an instance of <code>DivCommand</code>, passing in the 
     * give <code>Double</code> values, and then passing the <code>DivCommand</code>
     * to a new instance of <code>Client</code> for execution
     * @param button an instance of <code>DivButton</code>
     * @param value1 a <code>Double</code> that will correspond to the value of
     * the first JTextBox component
     * @param value2 a <code>Double</code> that will correspond to the value of
     * the second JTextBox component
     * @return the <code>String</code> Object returned by the Server instance in
     * response to the client request- will contain the result of the given DivCommand 
     */
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
            serverResponse = (String) client.execute(cmd).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
}
