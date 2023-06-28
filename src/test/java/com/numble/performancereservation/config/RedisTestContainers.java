package com.numble.performancereservation.config;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class RedisTestContainers {

    static {
        GenericContainer<?> redis =
            new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(
                6379);
        redis.start();
        System.setProperty("spring.data.redis.host", redis.getHost());
        System.setProperty("spring.data.redis.port", redis.getMappedPort(6379).toString());
    }
}
