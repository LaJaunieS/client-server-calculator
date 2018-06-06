package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import edu.uweo.java2.client.commands.AddCommand;
import edu.uweo.java2.client.commands.Receiver;

public class AddCommandTest {

    Receiver receiver = new Receiver();
            
    @Test
    public void testExecute() {
        AddCommand addCommand = new AddCommand();
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AddCommand);
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getResult()));
    }

    @Test
    public void testAddCommand() {
        AddCommand addCommand = new AddCommand();
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AddCommand);
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(0).equals(addCommand.getResult()));
    }

    @Test
    public void testAddCommandBigDecimalBigDecimal() {
        AddCommand addCommand = new AddCommand(BigDecimal.valueOf(1),BigDecimal.valueOf(2),0,0);
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AddCommand);
        assertTrue(BigDecimal.valueOf(1).equals(addCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(2).equals(addCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(3).equals(addCommand.getResult()));
    }

    @Test
    public void testAddCommandDoubleDouble() {
        AddCommand addCommand = new AddCommand(1.0,2.0,0,0);
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertTrue(addCommand instanceof AddCommand);
        assertEquals(BigDecimal.valueOf(1.0), addCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(2.0), addCommand.getOperand2());
        assertEquals(BigDecimal.valueOf(3.0), addCommand.getResult());
    }

}
