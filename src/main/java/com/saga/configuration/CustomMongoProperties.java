package com.ai.configuration;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class CustomMongoProperties {
    public MongoProperties reactive;
}
