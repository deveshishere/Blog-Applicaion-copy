package com.example.Blog.Application.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class ServerProperties {

    private static final Logger logger = LoggerFactory.getLogger(ServerProperties.class);

    private static Configuration configuration = null;

    static {
        configuration = new Configuration();
        try {
            configuration.initialize("serverProperty");
        } catch (IOException e) {
            logger.error("IOException in reading service property:{}",e.getMessage());
        }
    }

    public static String getPropertyValue(String property) {
        return configuration.getPropertyValue(property);
    }
}
