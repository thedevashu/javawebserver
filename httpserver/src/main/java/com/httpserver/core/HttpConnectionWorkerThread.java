package com.httpserver.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {

    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int _byte ;
            while ((_byte = inputStream.read()) >= 0) {
                System.out.print((char)_byte);
            }

            // read the input stream
            String html = "<html><head>  <title>Http Server</title> </head><body><h1>Build this using sockets</h1> </body> </html>";
            final String CRLF = "\r\n"; // 13, 10
            String response = "HTTP/1.1 200 OK" + CRLF + // status line HTTP version response code response message
                    "Content-Length: " + html.length() + CRLF +
                    CRLF + html;
            // write to the output stream
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
