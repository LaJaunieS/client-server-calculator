package edu.uweo.java2.client.commands;

import java.io.Serializable;
import java.math.BigDecimal;

/**A subclass of <code>AbstractCommand</code> in which an attached
 * Receiver multiplies operand1 by operand2, and result is then set from 
 * the result of the computation
 * @author slajaunie
 *
 */
public class MulCommand extends AbstractCommand  {

    private static final long serialVersionUID = 5573615746778988473L;

    /**Default constructor- sets operand1 and operand2 values to 
     * the <code>BigDecimal</code> value of 0, and workMillisMin and
     *workMillisMax are both set to the default of 500ms*/
    public MulCommand() {
        super();
    }
    
    /**Constructs a new MulCommand using the given BigDecimal 
     * operands, workMillisMin and
     * workMillisMax are both set to the given <code>double</code>,
     * and and result and receiver are set to <code>null</code>.
     *  If either operand is <code>null</code>, throws a 
     * <code>NullPointerException</code>
     * @param operand1 The first operand
     * @param operand2 The second operand
     *  @param workMillisMin the minimum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     * @param workMillisMax the maximum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     */
    public MulCommand(BigDecimal operand1, BigDecimal operand2,
            long workMillisMin, long workMillisMax) {
        super(operand1,operand2,workMillisMin,workMillisMax);
    }
    
    /**Calls the corresponding superclass constructor, where the given operand
     * values are converted to <code>BigDecimal</code> values, workMillisMin and
     * workMillisMax are both set to the given <code>double</code>,and result
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
     public MulCommand(double operand1, double operand2,
             double workMillisMin, double workMillisMax) {
        super(BigDecimal.valueOf(operand1), BigDecimal.valueOf(operand2),
                workMillisMin,workMillisMax);
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
