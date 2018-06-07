package edu.uweo.java2.client.commands;

import java.io.Serializable;

import edu.uweo.java2.server.Server;

/**Encapsulates a shutdown command sent to a Server 
 * @author slajaunie
 *
 */
public class ShutdownCommand extends AbstractCommand {
    /**Instantiates a new ShutdownCommand, calling the default super
     * constructor of <code>AbstractCommand</code>
     */
    public ShutdownCommand() {
        super();
    }
    
    /*
     * @see edu.uweo.java2.assignment9.AbstractCommand#execute()
     */
    @Override
    public void execute() {}
        
}
