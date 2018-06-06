package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.uweo.java2.client.commands.AbstractCommand;
import edu.uweo.java2.client.commands.AddCommand;
import edu.uweo.java2.client.commands.Receiver;

public class AbstractCommandTest {

    Receiver receiver = new Receiver();
        
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testAbstractCommandBigDecimalBigDecimal() {
        AddCommand addCommand = new AddCommand(BigDecimal.valueOf(1),BigDecimal.valueOf(2),0,0);
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AbstractCommand);
        assertTrue(BigDecimal.valueOf(1).equals(addCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(2).equals(addCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(3).equals(addCommand.getResult()));
        
        /*Should throw exception if either argument is null*/
        thrown.expect(NullPointerException.class);
        AddCommand addCommandNull = new AddCommand(null, BigDecimal.valueOf(1),0,0);
        
        thrown.expect(NullPointerException.class);
        AddCommand addCommandNull2 = new AddCommand(BigDecimal.valueOf(1),null,0,0);
    }

    @Test
    public void testAbstractCommand() {
        AddCommand addCommand = new AddCommand();
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AbstractCommand);
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getResult()));
    }

    @Test
    public void testExecute() {
        AddCommand addCommand = new AddCommand();
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AbstractCommand);
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getResult()));
    }

    @Test
    public void testGetReceiver() {
        AddCommand addCommand = new AddCommand(5,5,0,0);
        addCommand.setReceiver(receiver);
        assertEquals(receiver, addCommand.getReceiver());
    }

    @Test
    public void testSetReceiver() {
        AddCommand addCommand = new AddCommand(5,5,0,0);
        addCommand.setReceiver(receiver);
        assertEquals(receiver, addCommand.getReceiver());
    }

    @Test
    public void testGetOperand1() {
        AddCommand addCommand = new AddCommand(5,5,0,0);
        assertEquals(5.0, addCommand.getOperand1().floatValue(),.00001);
    }

    @Test
    public void testSetOperand1() {
        AddCommand addCommand = new AddCommand(5,5,0,0);
        addCommand.setOperand1(BigDecimal.valueOf(6));
        assertEquals(6.0,addCommand.getOperand1().floatValue(),.0001);
    }

    @Test
    public void testGetOperand2() {
        AddCommand addCommand = new AddCommand(5,5,0,0);
        assertEquals(5.0, addCommand.getOperand2().floatValue(),.00001);
    }

    @Test
    public void testSetOperand2() {
        AddCommand addCommand = new AddCommand(5,5,0,0);
        addCommand.setOperand2(BigDecimal.valueOf(6));
        assertEquals(6.0,addCommand.getOperand2().floatValue(),.0001);
    }

    @Test
    public void testGetResult() {
        AddCommand addCommand = new AddCommand();
        assertEquals(null,addCommand.getResult());
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AbstractCommand);
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getResult()));
    }

    @Test
    public void testSetResult() {
        AddCommand addCommand = new AddCommand();
        assertEquals(null,addCommand.getResult());
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AbstractCommand);
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getResult()));
    }
    
    @Test
    public void testGetWorkMillisMin() {
        AddCommand addCommand = new AddCommand();
        assertEquals(0,addCommand.getWorkMillisMin(),.00001);
    }

    @Test
    public void testGetWorkMillisMax() {
        AddCommand addCommand = new AddCommand();
        assertEquals(0,addCommand.getWorkMillisMax(),.00001);
    }

}
