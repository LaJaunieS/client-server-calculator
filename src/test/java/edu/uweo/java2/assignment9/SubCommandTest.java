package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import edu.uweo.java2.client.commands.Receiver;
import edu.uweo.java2.client.commands.SubCommand;

public class SubCommandTest {

    Receiver receiver = new Receiver();
    
    @Test
    public void testExecute() {
        SubCommand subCommand = new SubCommand(2.0,2.0,0,0);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        assertTrue(subCommand instanceof SubCommand);
        assertEquals(BigDecimal.valueOf(0.0), subCommand.getResult());
    }

    @Test
    public void testSubCommand() {
        SubCommand subCommand = new SubCommand();
        subCommand.setReceiver(receiver);
        subCommand.execute();
        assertTrue(subCommand instanceof SubCommand);
        assertTrue(BigDecimal.valueOf(0).equals(subCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(0).equals(subCommand.getOperand2()));
        assertEquals(BigDecimal.valueOf(0),subCommand.getResult());
    }

    @Test
    public void testSubCommandBigDecimalBigDecimal() {
        SubCommand subCommand = new SubCommand(BigDecimal.valueOf(1),BigDecimal.valueOf(2),0,0);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        assertTrue(subCommand instanceof SubCommand);
        assertTrue(BigDecimal.valueOf(1).equals(subCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(2).equals(subCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(-1).equals(subCommand.getResult()));
    }

    @Test
    public void testSubCommandDoubleDouble() {
        SubCommand subCommand = new SubCommand(1.0,2.0,0,0);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        assertTrue(subCommand instanceof SubCommand);
        assertEquals(BigDecimal.valueOf(1.0), subCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(2.0), subCommand.getOperand2());
        assertEquals(BigDecimal.valueOf(-1.0), subCommand.getResult());
    }

}
