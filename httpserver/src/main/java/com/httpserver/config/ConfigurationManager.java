package com.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpserver.util.Json;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myConfiguration;
    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            synchronized (ConfigurationManager.class) {
                if (myConfigurationManager == null)
                    myConfigurationManager = new ConfigurationManager();
            }
        }
        return myConfigurationManager;
    }

    public void loadConfiguration(String path) {
        // Read the configuration file
        // Parse the configuration file
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("Configuration file not found",e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("Error reading configuration file",e);
        }

        JsonNode conf;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("Error parsing configuration file",e);
        }
        try {
            myConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("Error parsing configuration file internal",e);
        }

    }

    /*
     * get current loaded configuration
     */
    public Configuration getConfiguration() {
        if(myConfiguration == null){
            throw new RuntimeException("Configuration not loaded");
        }
        return myConfiguration;
    }
}
