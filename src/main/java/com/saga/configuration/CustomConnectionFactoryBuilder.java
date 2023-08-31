package com.ai.configuration;

import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@RequiredArgsConstructor
@Getter
public class CustomConnectionFactoryBuilder {

    @Autowired
    private CustomR2bcProperties customR2bcProperties;

    public ConnectionFactoryBuilder connectionFactoryOptions() {

        ConnectionFactoryOptions.Builder builder = ConnectionFactoryOptions.builder();
        builder.option(ConnectionFactoryOptions.DRIVER, "pool");
        builder.option(ConnectionFactoryOptions.PROTOCOL, "mysql");
        builder.option(ConnectionFactoryOptions.HOST, this.customR2bcProperties.getUrl());
        builder.option(ConnectionFactoryOptions.PORT, Integer.parseInt(this.customR2bcProperties.getPort()));
        builder.option(ConnectionFactoryOptions.HOST, this.customR2bcProperties.getHost());

        String databaseName = customR2bcProperties.getDatabase().get("app");
        builder.option(ConnectionFactoryOptions.DATABASE, databaseName);

        String username = this.customR2bcProperties.getUsername();
        if (StringUtils.hasText(username)) {
            builder.option(ConnectionFactoryOptions.USER, username);
        }
        String password = this.customR2bcProperties.getPassword();
        if (StringUtils.hasText(password)) {
            builder.option(ConnectionFactoryOptions.PASSWORD, password);
        }
        return ConnectionFactoryBuilder.withOptions(builder);
    }

}
