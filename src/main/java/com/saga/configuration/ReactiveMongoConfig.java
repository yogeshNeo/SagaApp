package com.ai.configuration;

import com.mongodb.*;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(
        basePackages = {"com.ai.repository.mongo"},
        reactiveMongoTemplateRef = "reactiveMongoTemplate"
)
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    private CustomMongoProperties customMongoProperties;

    @Primary
    @Bean({"mongoClient"})
    public MongoClient reactiveMongoClientReactive() {
        MongoClientSettings mongoClientSettings = this.createMongoClientSettings(this.customMongoProperties.getReactive());
        return MongoClients.create(mongoClientSettings);
    }

    @Primary
    @Bean({"reactiveMongoTemplate"})
    public ReactiveMongoTemplate reactiveMongoTemplateForReactiveDB() {
        MongoClient mongoClient = this.reactiveMongoClientReactive();
        String db = this.customMongoProperties.getReactive().getDatabase();
        return new ReactiveMongoTemplate(mongoClient, db);
    }

    private MongoClientSettings createMongoClientSettings(MongoProperties mongoProperties) {
        ConnectionString ConnectionString = new ConnectionString(mongoProperties.getUri());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().readConcern(ReadConcern.DEFAULT).writeConcern(WriteConcern.MAJORITY).readPreference(ReadPreference.primary()).applyConnectionString(ConnectionString).build();
        return mongoClientSettings;
    }

    protected String getDatabaseName() {
        return this.customMongoProperties.getReactive().getDatabase();
    }
}
