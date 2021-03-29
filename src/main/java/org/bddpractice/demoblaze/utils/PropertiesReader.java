package org.bddpractice.demoblaze.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author sbryt on 29/03/2021
 */
public class PropertiesReader {
    private static PropertiesReader propertiesReader = null;
    private Properties properties;

    public static PropertiesReader getInstance(){
        if (propertiesReader == null){
            propertiesReader = new PropertiesReader();
        }
        return propertiesReader;
    }

    PropertiesReader()  {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propName){
       return properties.getProperty(propName);
    }
}
