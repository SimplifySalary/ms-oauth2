package com.ss.oauth2.configuration;

import com.ss.oauth2.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.client.RestTemplate;

/**
 * @author biandra
 */
@Configuration
public class Oauth2ServerConfig {

    @Autowired
    @Qualifier("redisConnectionFactoryBean")
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStoreBean() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return customUserDetailsService;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
