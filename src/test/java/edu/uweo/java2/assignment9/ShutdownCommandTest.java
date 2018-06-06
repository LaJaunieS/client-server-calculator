package edu.uweo.java2.assignment9;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.uweo.java2.client.commands.ShutdownCommand;

public class ShutdownCommandTest {

    @Test
    public void testShutdownCommand() {
        ShutdownCommand shutdown = new ShutdownCommand();
        assertTrue(shutdown instanceof ShutdownCommand);
    }

}
