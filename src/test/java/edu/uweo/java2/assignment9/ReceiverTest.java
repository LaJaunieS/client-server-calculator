package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.uweo.java2.client.commands.AddCommand;
import edu.uweo.java2.client.commands.DivCommand;
import edu.uweo.java2.client.commands.MulCommand;
import edu.uweo.java2.client.commands.Receiver;
import edu.uweo.java2.client.commands.SubCommand;

public class ReceiverTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testActionAddCommand() {
        Receiver receiver = new Receiver();
        AddCommand add = new AddCommand(4.0,5.0,0,0);
        add.setReceiver(receiver);
        receiver.action(add);
        assertEquals(9.0,add.getResult().floatValue(),.0001);
        
    }

    @Test
    public void testActionSubCommand() {
        Receiver receiver = new Receiver();
        SubCommand sub = new SubCommand(4.0,5.0,0,0);
        sub.setReceiver(receiver);
        receiver.action(sub);
        assertEquals(-1.0,sub.getResult().floatValue(),.0001);
        
    }

    @Test
    public void testActionMulCommand() {
        Receiver receiver = new Receiver();
        MulCommand mul = new MulCommand(4.0,5.0,0,0);
        mul.setReceiver(receiver);
        receiver.action(mul);
        assertEquals(20.0,mul.getResult().floatValue(),.0001);
        
    }

    @Test
    public void testActionDivCommand() {
        Receiver receiver = new Receiver();
        DivCommand div = new DivCommand(4.0,2.0,0,0);
        div.setReceiver(receiver);
        receiver.action(div);
        assertEquals(2.0,div.getResult().floatValue(),.0001);
        
        DivCommand divZero1 = new DivCommand(0.0,2.0,0,0);
        DivCommand divZero2 = new DivCommand(2.0,0.0,0,0);
        divZero1.setReceiver(receiver);
        divZero2.setReceiver(receiver);
        receiver.action(divZero1);
        receiver.action(divZero2);
        assertEquals(0.0,divZero1.getResult().floatValue(),.0001);
        assertEquals(0.0,divZero2.getResult().floatValue(),.0001);
        
        
    }

    @Test
    public void testActionNAKCommand() {
        /////
    }

}
