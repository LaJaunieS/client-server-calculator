package edu.uweo.java2.guiButtons;

import java.math.BigDecimal;

import javax.swing.JButton;

import edu.uweo.java2.client.commands.AbstractCommand;

/**Encapsulates a custom button (avoiding having to have a million if
 * statements in the listener implementation)
 * @author slajaunie
 *
 */
public abstract class AbstractButton extends JButton {
    ButtonReceiver receiver;
    /*Will be the value of textBox value 1*/
    Double value1;
    /*Will be the value of textBox value 1*/
    Double value2;
    //Double result;
    
    /**Constructs a new AbstractButton
     * @param text The text included in the button, same as 
     * <code>new JButton(String text)"</code>
     * @param value1 a <code>Double</code> that will be the value of the first 
     * JTextBox component
     * @param value2 a <code>Double</code> that will be the value of the second
     * JTextBox component
     */
    public AbstractButton(String text,Double value1, Double value2) {
        super(text);
        this.value1 = value1;
        this.value2 = value2;
    }
    
    /**Will be implemented by the AbstractButton sub-class
     */
    public abstract AbstractCommand execute();
    
    /**Gets the instance of this AbstractButton's ButtonReceiver
     * @return this AbstractButton's ButtonReceiver instance
     */
    public ButtonReceiver getReceiver() {
        return this.receiver;
    }
    
    /**Sets the instance of this AbstractButton's ButtonReceiver
     * @param receiver the ButtonReceiver of this AbstractButton
     */
    public void setReceiver(ButtonReceiver receiver) {
        this.receiver = receiver;
    }

    /**Gets the first value of this AbstractButton (corresponding to the 
     * value of the first JTextBox component
     * @return the first value of this AbstractButton 
     */
    public Double getValue1() {
        return value1;
    }

    /**Sets the value1 field of this AbstractButton
     * @param value1 a <code>Double</code> that is to be the first value of 
     * this AbstractButton
     */
    public void setValue1(Double value1) {
        this.value1 = value1;
    }
    
    /**Gets the second value of this AbstractButton (corresponding to the 
     * value of the second JTextBox component
     * @return the second value of this AbstractButton 
     */
    public Double getValue2() {
        return value2;
    }

    /**Sets the value2 field of this AbstractButton
     * @param value2 a <code>Double</code> that is to be the second value of 
     * this AbstractButton
     */
    public void setValue2(Double value2) {
        this.value2 = value2;
    }
    
}
