package edu.uweo.java2.guiButtons;

import java.math.BigDecimal;

import edu.uweo.java2.client.commands.AbstractCommand;

public class MulButton extends AbstractButton {
    
    /**Constructs a new MulButton by invoking the constructor of the
     * superclass <code>AbstractButton</code>
     * @param text The text included in the button, same as 
     * <code>new JButton(String text)"</code>
     * @param value1 a <code>Double</code> that will be the value of the first 
     * JTextBox component
     * @param value2 a <code>Double</code> that will be the value of the second
     * JTextBox component
     */
    public MulButton(String text, Double value1, Double value2) {
        super(text,value1, value2);
    }
    
    /**Executes this command, by invoking the ButtonReceiver's <code>action</code>
     * method, passing this command as an argument. 
     * @return serverResponse a <code>String</code> that will be the computed result
     * executed by the server in response to the appropriate command, 
     * and returned to the client via an Object stream
     * @see edu.uweo.java2.guiButtons.AbstractButton#execute()
     */
    @Override
    public BigDecimal execute() {
        BigDecimal serverResponse = 
                this.getReceiver().action(this, value1, value2);
        return serverResponse;
    }
}
