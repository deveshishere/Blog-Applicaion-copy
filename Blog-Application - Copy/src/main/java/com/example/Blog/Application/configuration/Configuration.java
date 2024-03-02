package com.example.Blog.Application.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Order(2)
public class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    private Properties properties = new Properties();
    private InputStream inputStream = null;

    public void initialize(String propertyName) throws IOException {
        try {

//            String property = System.getProperty(propertyName);
            String property = "E:/application File/application.properties";

            if (property != null) {
                inputStream = new FileInputStream(property);
                properties.load(inputStream);
            }
        } catch (IOException ioException) {
            logger.error("Exception in reading the file: ", ioException.getMessage());
            throw ioException;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    logger.error("Couldn't close the stream: ", ioException.getMessage());
                    throw ioException;
                }
            }
        }
    }

    public String getPropertyValue(String property) {
        String value = properties.getProperty(property);
        if (value == null || value.isEmpty()) {
            logger.error("Please set up value of " + property + ".");
            value = "";
        }
        return value;
    }
}