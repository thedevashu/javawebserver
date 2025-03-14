"# javawebserver" 

## *Overview*
~~ HttpServer.java ~~ is the main driver class for a simple HTTP server. The server reads configuration settings from a JSON file, opens a socket connection, listens for incoming requests, and responds with a basic HTML page. The server handles one client connection at a time.

### *Steps*
-    Read the Configuration File

 -   The server reads configuration settings (such as port and webroot) from a JSON file using the ConfigurationManager class.
    Open a Socket Connection

-    The server opens a socket connection on the specified port.
    Listen for Incoming Requests

-    The server listens for incoming client connections.
    Handle Client Connection

-   When a client connects, the server reads the input stream and writes a simple HTML response to the output stream.
    Close Connections

-    The server closes the input stream, output stream, client socket, and server socket after handling the request.

### *Key Components*
* ConfigurationManager: Singleton class used to load and retrieve configuration settings.
* ServerSocket: Used to create a server socket that listens for client connections.
* Socket: Represents a client connection.
* InputStream: Used to read data from the client.
* OutputStream: Used to send data to the client.
### *Configuration File*
The configuration file (http.json) should be located in the resources directory and contain the necessary settings for the server, such as the port number and webroot.

### *Running the Server*
To run the server, execute the main method in the HttpServer class. The server will start, read the configuration, and listen for incoming client connections. When a client connects, the server will respond with a simple HTML page and then close the connection.