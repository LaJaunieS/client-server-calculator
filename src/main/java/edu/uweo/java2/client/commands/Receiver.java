package edu.uweo.java2.client.commands;


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.ClientServerTester;

/**Encapsulates a Receiver object, which performs an action based on its 
 * given instance of an <code>AbstractCommand</code> subclass
 * @author slajaunie
 *
 */
public class Receiver {
    
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);
    
    /*Used for testing behavior without having to have a server connection listening*/
    ClientServerTester cst = new ClientServerTester();
        
    /**Executes a given <code>AddCommand</code>, adding the given command's operand1
     * with operand2 and setting the given command's result field to the result of that
     * computation
     * @param command an instance of <code>AddCommand</code>
     */
    public void action(AddCommand command) {
        log.info("add command detected");
        command.setResult(command.getOperand1().add(command.getOperand2()));
        System.out.println("addCommand result: " + command.getResult().floatValue());
        
    }
    
    /**Executes a given <code>SubCommand</code>, subtracting the given command's operand2
     * from operand1 and setting the given command's result field to the result of that
     * computation
     * @param command an instance of <code>SubCommand</code>
     */
    public void action(SubCommand command) {
        log.info("subtract command detected");
        command.setResult(command.getOperand1().subtract(command.getOperand2()));
        System.out.println("SubCommand result: " + command.getResult().floatValue());
    }
    
    /**Executes a given <code>MulCommand</code>, multiplying the given command's operand1
     * by operand2 and setting the given command's result field to the result of that
     * computation
     * @param command an instance of <code>MulCommand</code>
     */
    public void action(MulCommand command) {
        log.info("multiply command detected");
        command.setResult(command.getOperand1().multiply(command.getOperand2()));
        System.out.println("mulCommand result: " + command.getResult().floatValue());
    }
    
    /**Executes a given <code>DivCommand</code>, dividing the given command's operand1
     * into operand2 and setting the given command's result field to the result of that
     * computation. If a given command's operand1 or operand2 fields are set to the 
     * <code>BigDecimal</code> value of 0, it logs that an <code>ArithmeticException</code>
     * was thrown and sets the given command's result value to the <code>BogDecimal</code>
     * value of 0.
     * @param command an instance of <code>DivCommand</code>
     */
    public void action(DivCommand command) {
        log.info("divide command detected");
        BigDecimal zero = BigDecimal.valueOf(0);
        try {
            command.setResult(command.getOperand1().divide(command.getOperand2()));
        } catch (ArithmeticException ex) {
            log.warn("An arithmetic exception was thrown, setting result to zero");
            command.setResult(zero);
        }
        System.out.println("divCommand result: " + command.getResult().floatValue());
        
    }
    
    /**Will be used for testing unrecognized commands*/
    public void action(Object obj) {
        log.info("NAKCommand detected");
    }
}
