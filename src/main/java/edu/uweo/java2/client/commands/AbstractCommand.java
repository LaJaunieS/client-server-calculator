package edu.uweo.java2.client.commands;

import java.io.Serializable;
import java.math.BigDecimal;


/**Superclass for command objects. 
 * @author slajaunie
 *
 */
public abstract class AbstractCommand implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -8852118822270515342L;
    transient Receiver receiver;
    /**The first operand used to compute the result*/
    BigDecimal operand1;
    /**The first operand used to compute the result*/
    BigDecimal operand2;
    /**The result of performing a computation on the operands*/
    BigDecimal result;
    
    /**Can be used to provide a message from the server relating to execution 
     * of the class*/
    String message;
    
    double workMillisMin;
    double workMillisMax;

    /**Default constructor- sets operand1 and operand2 values to 
     * the <code>BigDecimal</code> value of 0. Sets the values of 
     * <code>workMillisMin</code> and <code>workMillisMax</code> to 0.
     */
    public AbstractCommand() {
        this(BigDecimal.valueOf(0),BigDecimal.valueOf(0),0,0);
    }
    
    /**Constructs a new AbstractCommand using the given BigDecimal 
     * operands. If either operand is <code>null</code>, throws a 
     * <code>NullPointerException</code>
     * @param operand1 The first operand
     * @param operand2 The second operand
     *@param workMillisMin the minimum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     * @param workMillisMax the maximum boundary for the randomly-selected length of time
     * (in milliseconds) that the command will begin to execute
     * @throws NullPointerException if either operand is <code>null</code>
     */
    public AbstractCommand(BigDecimal operand1, BigDecimal operand2, 
                            double workMillisMin, double workMillisMax) 
            throws NullPointerException {
        if (operand1 == null || operand2 == null)
            throw new NullPointerException("operands must not be null");
        else 
            this.operand1 = operand1;
            this.operand2 = operand2;
            
        this.receiver = null;
        this.result = null;
        this.workMillisMax = workMillisMax;
        this.workMillisMin = workMillisMin;
    }

    /**
     *A subclass will execute this command 
     */
    public abstract void execute();

    /**Get the result of executing this command. Initially set to
     * null
     * @return a <code>BigDecimal</code> that is the result of executing
     * this command using the given operand1 and operand2 
     */
    public BigDecimal getResult() {
        return result;
    }

    
    /**Gets the receiver for this command. Initially set 
     * to <code>null</code>
     * @return the instance of <code>Receiver</code> associated
     * with this command
     */
    public Receiver getReceiver() {
        return receiver;
    }

    /**Sets the receiver for the commands
     * @param receiver an instance of <code>Receiver</code>
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    /**Gets the value of operand1, of type <code>BigDecimal</code> 
     * @return the value of operand1
     */
    public BigDecimal getOperand1() {
        return operand1;
    }

    /**Sets the value of operand1
     * @param operand1 a <code>BigDecimal</code> that is to be the
     * value of operand1
     */
    public void setOperand1(BigDecimal operand1) {
        this.operand1 = operand1;
    }
    
    /**Gets the value of operand2, of type <code>BigDecimal</code> 
     * @return the value of operand2
     */
    public BigDecimal getOperand2() {
        return operand2;
    }

    /**Sets the value of operand1
     * @param operand2 a <code>BigDecimal</code> that is to be the
     * value of operand2
     */
    public void setOperand2(BigDecimal operand2) {
        this.operand2 = operand2;
    }

    
    /**Sets the result of executing this command.
     * @param result a <code>BigDecimal</code> that is to be the result
     * of this command
     */
    public void setResult(BigDecimal result) {
        this.result = result;
    }

    /**Gets the value of getWorkMillisMin, of type <code>double</code> 
     * @return the value of getWorkMillisMin
     */
    public double getWorkMillisMin() {
        return workMillisMin;
    }

    /**Gets the value of getWorkMillisMax, of type <code>double</code> 
     * @return the value of getWorkMillisMax
     */
    public double getWorkMillisMax() {
        return workMillisMax;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
