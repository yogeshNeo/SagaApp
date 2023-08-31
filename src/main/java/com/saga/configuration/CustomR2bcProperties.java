package com.ai.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "spring.r2dbc")
public class CustomR2bcProperties {
    private String url;
    private String username;
    private String password;
    private String host;
    private String port;
    private Map<String, String> database;
}

