package com.httpserver;

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

        System.out.println("Port: " + ConfigurationManager.getInstance().getConfiguration().getPort());
        System.out.println("Webroot: " + ConfigurationManager.getInstance().getConfiguration().getWebroot());

        System.out.println("Server stopped");
    }
}
