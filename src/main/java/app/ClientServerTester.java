package app;

import java.io.Serializable;

/*repository for values which demonstrate success/failure
 * of operations relating to the exchange between a client and a server-
 * for testing purposes
 * To Test the values in this class, initiate at least one
 *  client/server exchange, and one client exchange returning 
 *  a "NAK" response- the exchange will then populate certain
 *  values in this class which can be examined for testing purposes
 *  If any of the fields return false or null, the related operation
 *  was not successful
 * 
 */
public class ClientServerTester implements Serializable {
    /*Server responsibility*/
    public boolean serverSocketOpened;
    public boolean serverListening; 
    public boolean clientConnectionAcceptedByServer;
    public boolean commandObjAcceptedAndResponseSentToClient;
    public boolean threadPoolSuccessfullyShutdown;
    public boolean serverSocketClosed;
    
    /*Client responsibility*/
    public boolean commandObjWrittenToOutputStream;
    public boolean ServerAckReadByClient;
    public boolean serverNAKReadByClient;
    
}
