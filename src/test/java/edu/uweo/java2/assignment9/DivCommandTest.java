package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.uweo.java2.client.commands.DivCommand;
import edu.uweo.java2.client.commands.Receiver;

public class DivCommandTest {
    Receiver receiver = new Receiver();
    
    @Test
    public void testExecute() {
        DivCommand divCommand = new DivCommand(2.0,2.0,0,0);
        divCommand.setReceiver(receiver);
        divCommand.execute();
        assertTrue(divCommand instanceof DivCommand);
        assertTrue(BigDecimal.valueOf(1).equals(divCommand.getResult()));
    }

    @Test
    public void testDivCommand() {
        DivCommand divCommand = new DivCommand();
        Receiver newReceiver = new Receiver();
        divCommand.setReceiver(newReceiver);
        divCommand.execute();
        assertTrue(divCommand instanceof DivCommand);
        assertTrue(BigDecimal.valueOf(0).equals(divCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(0).equals(divCommand.getOperand2()));
        assertEquals(BigDecimal.valueOf(0),divCommand.getResult());
    }

    @Test
    public void testDivCommandBigDecimalBigDecimal() {
        DivCommand divCommand = new DivCommand(BigDecimal.valueOf(1),BigDecimal.valueOf(2),0,0);
        divCommand.setReceiver(receiver);
        divCommand.execute();
        assertTrue(divCommand instanceof DivCommand);
        assertTrue(BigDecimal.valueOf(1).equals(divCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(2).equals(divCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(0.5).equals(divCommand.getResult()));
    }

    @Test
    public void testDivCommandDoubleDouble() {
        DivCommand divCommand = new DivCommand(1.0,2.0,0,0);
        divCommand.setReceiver(receiver);
        divCommand.execute();
        assertTrue(divCommand instanceof DivCommand);
        assertEquals(BigDecimal.valueOf(1.0), divCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(2.0), divCommand.getOperand2());
        assertEquals(BigDecimal.valueOf(0.5), divCommand.getResult());
    }

}
