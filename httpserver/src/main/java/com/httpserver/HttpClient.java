package com.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;

public class HttpClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client started");

        Socket socket = new Socket("localhost", 8080);

        InputStream inputStream = socket.getInputStream();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
        String serverMsg ;

        while((serverMsg = bfr.readLine()) != null){
            
            System.out.println( serverMsg);
        }

        
    }
}
