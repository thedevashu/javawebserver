package com.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
    private static ObjectMapper objectMapper = getObjectMapper() ;
    

    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature
        .FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    public static JsonNode parse(String jsonSrc) throws IOException{
        return objectMapper.readTree(jsonSrc);
    }

    public static <A> A fromJson(JsonNode node,Class<A> clazz) throws JsonProcessingException{
        return objectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object obj){
        return objectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException{
        return generateJson(node,false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException{
        return generateJson(node,true);
    }



    private static String generateJson(Object obj,boolean pretty) throws JsonProcessingException{
        ObjectWriter writer = objectMapper.writer();
        if(pretty){
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        }
        return writer.writeValueAsString(obj);
    }
}
