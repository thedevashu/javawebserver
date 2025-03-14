package com.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.httpserver.config.Configuration;
import com.httpserver.config.ConfigurationManager;

/**
 * Driver class for the HTTP server
 * Step 1. Read the configuration file
 * Step 2. Open a socket connection
 * Step 3. Listen for multiple incoming requests
 * Step 4. Create a new thread for each request
 * Step 5. Read and write data to the client to system
 *
 */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server started");
        ConfigurationManager.getInstance()
        .loadConfiguration("httpserver\\src\\resources\\http.json");

        Configuration config = ConfigurationManager.getInstance().getConfiguration();

        System.out.println("Port: " + ConfigurationManager.getInstance().getConfiguration().getPort());
        System.out.println("Webroot: " + ConfigurationManager.getInstance().getConfiguration().getWebroot());
        try {
            ServerSocket serverSocket = new ServerSocket(config.getPort());
            Socket socket = serverSocket.accept();

            System.out.println("Client connected");

            InputStream inputStream = socket.getInputStream();

            OutputStream outputStream = socket.getOutputStream();

            //read the input stream
            String html = "<html><head>  <title>Http Server</title> </head><body><h1>Build this using sockets</h1> </body> </html>";
            final String CRLF = "\r\n"; // 13, 10
            String response =
                    "HTTP/1.1 200 OK" +CRLF+//status line HTTP version response code response message
                            "Content-Length: " + html.length() + CRLF +
                            CRLF+ html;
            //write to the output stream
            outputStream.write(response.getBytes());
            
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Server stopped");
    }
}
