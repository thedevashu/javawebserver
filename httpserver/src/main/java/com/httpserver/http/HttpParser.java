package com.httpserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;

    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII) ;
        HttpRequest request = new HttpRequest();

        try {
            parseRequestLine(reader,request);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        parseHeaders(reader,request);
        parseBody(reader,request);
        return request;
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {

        StringBuilder processingDataBuffer = new StringBuilder();

        boolean methodParsed = false;
        boolean requestTargetParsed = false;
        int _byte;
        while ((_byte = reader.read()) >=0 ){
            if(_byte == CR){
                _byte = reader.read();
                if (_byte == LF){
                    LOGGER.debug("Request Line VERSION to process : {}" , processingDataBuffer.toString());
                    return;
                }

            }

            if(_byte == SP){
                if(!methodParsed){
                LOGGER.debug("Request Line METHOD to process : {}" , processingDataBuffer.toString());
                request.setMethod(processingDataBuffer.toString());
                methodParsed=true;
                }else if(!requestTargetParsed){
                    LOGGER.debug("Request Line REQ TARGET to Process : {}" , processingDataBuffer.toString());
                requestTargetParsed=true;
                }else{
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                processingDataBuffer.delete(0,processingDataBuffer.length());

            }else{
                processingDataBuffer.append((char)_byte);
                if (!methodParsed){
                    if (processingDataBuffer.length() > HttpMethod.MAX_LENGTH){
                        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
                    }
                }
            }
        }

    }

}
