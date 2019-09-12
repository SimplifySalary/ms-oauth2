package com.ss.oauth2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author biandra
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.oauth2")
@Data
public class RedisProperties {

    private String host;
    private int port;
}
