package edu.uweo.java2.guiButtons;

import java.io.IOException;
import java.math.BigDecimal;

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
     * @return the <code>BigDecimal</code> Object returned by the Server instance in
     * response to the client request- will represent the result of the given AddCommand 
     */
    public BigDecimal action(AddButton button, Double value1, Double value2) {
        AddCommand cmd = new AddCommand(value1,value2,100,100);
        BigDecimal serverResponse = this.execute(cmd);
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
     * @return the <code>BigDecimal</code> Object returned by the Server instance in
     * response to the client request- will represent the result of the given SubCommand 
     */
    public BigDecimal action(SubButton button, Double value1, Double value2) {
        SubCommand cmd = new SubCommand(value1, value2, 100,100);
        BigDecimal serverResponse= this.execute(cmd);
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
     * @return the <code>BigDecimal</code> Object returned by the Server instance in
     * response to the client request- will represent the result of the given MulCommand 
     */
    public BigDecimal action(MulButton button, Double value1, Double value2) {
        MulCommand cmd = new MulCommand(value1, value2, 100,100);
        BigDecimal serverResponse = this.execute(cmd);
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
     * @return the <code>BigDecimal</code> Object returned by the Server instance in
     * response to the client request- will represent the result of the given DivCommand 
     */
    public BigDecimal action(DivButton button, Double value1, Double value2) {
        DivCommand cmd = new DivCommand(value1, value2, 100,100);
        BigDecimal serverResponse = this.execute(cmd);
        return serverResponse;
    }
    
    /**Executes the given appropriate command in response to an AbstractButton event.
     * Instantiates a new <code>Client</code> at port 4885 and sends the request
     * to a Server at that port location
     * @param cmd an appropriate instance of <code>AbstractCommand</code> that will be 
     * executed by a Server in response to the client's request
     * @return a <code>BigDecimal</code> returned by the Server in response to the client
     * request, which will represent the result of the given AbstractCommand
     */
    public BigDecimal execute(AbstractCommand cmd) {
        Client client = new Client(4885);
        BigDecimal serverResponse = null;
        try {
            serverResponse = client.execute(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
}
