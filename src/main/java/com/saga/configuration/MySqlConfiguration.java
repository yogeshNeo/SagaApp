package com.ai.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.ai.repository")
public class MySqlConfiguration {
    @Autowired
    private CustomR2bcProperties customR2bcProperties;

    @Autowired
    private CustomConnectionFactoryBuilder customConnectionFactoryBuilder;

    @Bean("connectionFactory")
    ConnectionFactory appConnectionFactory() {
        return customConnectionFactoryBuilder.connectionFactoryOptions().build();
    }

    @Bean
    public ConnectionFactoryInitializer r2dbcScriptDatabaseInitializer() {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(this.appConnectionFactory());
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }

    @Bean
    ReactiveTransactionManager transactionManager() {
        ConnectionFactory connectionFactory = this.appConnectionFactory();
        return new R2dbcTransactionManager(connectionFactory);
    }

}