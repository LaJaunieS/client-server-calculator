package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import edu.uweo.java2.client.commands.MulCommand;
import edu.uweo.java2.client.commands.Receiver;

public class MulCommandTest {
Receiver receiver = new Receiver();
    
    @Test
    public void testExecute() {
        MulCommand mulCommand = new MulCommand(2.0,2.0,0,0);
        mulCommand.setReceiver(receiver);
        mulCommand.execute();
        assertTrue(mulCommand instanceof MulCommand);
        assertEquals(4.0, mulCommand.getResult().floatValue(),.00001);
    }

    @Test
    public void testMulCommand() {
        MulCommand mulCommand = new MulCommand();
        mulCommand.setReceiver(receiver);
        mulCommand.execute();
        assertTrue(mulCommand instanceof MulCommand);
        assertTrue(BigDecimal.valueOf(0).equals(mulCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(0).equals(mulCommand.getOperand2()));
        assertEquals(BigDecimal.valueOf(0),mulCommand.getResult());
    }

    @Test
    public void testMulCommandBigDecimalBigDecimal() {
        MulCommand mulCommand = new MulCommand(BigDecimal.valueOf(1),BigDecimal.valueOf(2),0,0);
        mulCommand.setReceiver(receiver);
        mulCommand.execute();
        assertTrue(mulCommand instanceof MulCommand);
        assertTrue(BigDecimal.valueOf(1).equals(mulCommand.getOperand1()));
        assertTrue(BigDecimal.valueOf(2).equals(mulCommand.getOperand2()));
        assertTrue(BigDecimal.valueOf(2).equals(mulCommand.getResult()));
    }

    @Test
    public void testMulCommandDoubleDouble() {
        MulCommand mulCommand = new MulCommand(1.0,2.0,0,0);
        mulCommand.setReceiver(receiver);
        mulCommand.execute();
        assertTrue(mulCommand instanceof MulCommand);
        assertEquals(BigDecimal.valueOf(1.0), mulCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(2.0), mulCommand.getOperand2());
        assertEquals(2.0, mulCommand.getResult().floatValue(),.000001);
    }
}
