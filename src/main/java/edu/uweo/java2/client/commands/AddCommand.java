package edu.uweo.java2.client.commands;

import java.math.BigDecimal;

/**A subclass of <code>AbstractCommand</code> in which an 
 * attached Receiver adds operand1 to operand2, 
 * and result is then set from the result of the
 * computation
 * @author slajaunie
 *
 */
public class AddCommand extends AbstractCommand {

    private static final long serialVersionUID = 4958594491573822388L;

    /**
     *The default constructor- calls the superclass constructor, where operand 
     *values are set to the <code>BigDecimal</code> value of 0, workMillisMin and
     *workMillisMax are both set to the default of 500ms, 
     * and result and receiver are set to <code>null</code> 
     */
    public AddCommand() {
        super();
    }
    
    /**Calls the corresponding superclass constructor, where operand values
     * are set to the given <code>BigDecimal</code> values, workMillisMin and
     * workMillisMax are both set to the given <code>double</code>, and result and 
     * receiver are set to <code>null</code>. Note that neither given operand
     * can be <code>null</code>, otherwise the superclass constructor throws a 
     * <code>NullPointerException</code>
     * @param operand1 the first <code>BigDecimal</code> operand used to compute result
     * @param operand2 the second <code>BigDecimal</code> operand used to compute result
     * @param workMillisMin the minimum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     * @param workMillisMax the maximum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     */
    public AddCommand(BigDecimal operand1, BigDecimal operand2, 
                    double workMillisMin, double workMillisMax) {
        super(operand1,operand2,workMillisMin,workMillisMax);
    }
    
    /**Calls the corresponding superclass constructor, where the given operand
     * values are converted to <code>BigDecimal</code> values, workMillisMin and
     * workMillisMax are both set to the given <code>double</code>, and result
     * and receiver are set to <code>null</code>. Note that neither given
     * operand can be <code>null</code>, otherwise the superclass constructor
     * throws a <code>NullPointerException</code> 
     * @param operand1 a <code>double</code> that will be the first operand used 
     * to compute result, after conversion to a <code>BigDecimal</code> value
     * @param operand2 a <code>double</code> that will be the second operand used 
     * to compute result, after conversion to a <code>BigDecimal</code> value
     * @param workMillisMin the minimum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     * @param workMillisMax the maximum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     */
    public AddCommand(double operand1, double operand2, 
                        double workMillisMin, double workMillisMax) {
        super(BigDecimal.valueOf(operand1), BigDecimal.valueOf(operand2), 
                    workMillisMin, workMillisMax);
    }
    
    /**Executes this command, by invoking the Receiver's <code>action</code>
     * method, passing this command as an argument
     * @see edu.uweo.java2.client.commands.AbstractCommand#execute()
     */
    @Override
    public void execute() {
        this.getReceiver().action(this);
    }

}
