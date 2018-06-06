# client-server-calculator
Implements a GUI interface on earlier app calculating result of two given values, 
using the command pattern, and a client/server exchange.
Each button event generates a new client request to the server. The server uses the 
request to queue and then open a new thread for each request. It then returns
the result of the command as a string back to the Client object, which can be displayed
in the GUI interface.

To run, first run the ServerDriver class to open the server for accepting requests.
Then run the GUI class to open the GUI.
