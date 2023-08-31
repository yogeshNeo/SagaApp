package com.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoReactiveAutoConfiguration.class, MongoReactiveDataAutoConfiguration.class})
public class AIApplication {
    private static Environment env;

    public AIApplication(Environment env) {
        AIApplication.env = env;
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(AIApplication.class, args);
        String profile = env.getActiveProfiles()[0];
        String hostname = InetAddress.getLocalHost().getHostAddress();
        String info = env.getProperty("spring.application.name") + " App running on http://" + hostname + ":" + env.getProperty("server.port") + " for " + profile + " profile";

        log.info(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        log.info(" <<<<<< " + info + " >>>>>");
        log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

}
