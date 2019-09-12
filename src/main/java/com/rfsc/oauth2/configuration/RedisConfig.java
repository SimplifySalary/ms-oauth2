package com.rfsc.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author biandra
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactoryBean() {
        RedisConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(
                redisProperties.getHost(),
                redisProperties.getPort());
        return redisConnectionFactory;
    }


}
