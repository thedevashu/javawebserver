package com.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.httpserver.config.Configuration;
import com.httpserver.config.ConfigurationManager;
import com.httpserver.core.ServerListenerThread;

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

    private final static Logger LOGGER= LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {
        LOGGER.info("Server started");
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.loadConfiguration("httpserver\\src\\resources\\http.json");

        Configuration config = configManager.getConfiguration();

        LOGGER.info("Port: " + config.getPort());
        LOGGER.info("Webroot: " + config.getWebroot());
        try {
            ServerListenerThread serverListenerThread =
                 new ServerListenerThread(config.getPort(), config.getWebroot());

                 serverListenerThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        LOGGER.info("Server stopped");
    }
}
