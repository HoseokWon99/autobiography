package com.autobiography.users.config.redis;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("spring.data.redis")
public class RedisProperties {
    private String host;
    private int port;
}
