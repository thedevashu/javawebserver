package com.httpserver.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class HttpParserTest {

    private static  HttpParser httpParser;

    @BeforeAll
    public static void beforeClass(){
        httpParser = new HttpParser();
    }
    @Test
    void parseHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidTestCase()
            );
        } catch (HttpParsingException e) {
            fail(e);
        }
        assertEquals(request.getMethod(),HttpMethod.GET);
    }

    private InputStream generateValidTestCase(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8\r\n" +
                "Sec-GPC: 1\r\n" +
                "Accept-Language: en-US,en;q=0.6\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n"

;

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    @Test
    void parseHttpRequestBadMethod1() {
        try {
            HttpRequest request =httpParser.parseHttpRequest(
                    generateValidBadTestCase1()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }

    }


    @Test
    void parseHttpRequestBadMethod2() {
        try {
            HttpRequest request =httpParser.parseHttpRequest(
                    generateValidBadTestCase2()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(),HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }

    }

    @Test
    void parseHttpRequestBadMethod3() {
        try {
            HttpRequest request =httpParser.parseHttpRequest(
                    generateValidBadTestCaseRequestInvalidNumbersOfItems()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getErrorCode(),HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }

    }


    private InputStream generateValidBadTestCase1(){
        String rawData = "Ge / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8\r\n" +
                "Sec-GPC: 1\r\n" +
                "Accept-Language: en-US,en;q=0.6\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n"

                ;

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateValidBadTestCase2(){
        String rawData = "Gettt / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +

                "Accept-Encoding: gzip, deflate, br, zstd\r\n"

                ;

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateValidBadTestCaseRequestInvalidNumbersOfItems(){
        String rawData = "GET / AAAAA HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +

                "Accept-Encoding: gzip, deflate, br, zstd\r\n"

                ;

        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
}