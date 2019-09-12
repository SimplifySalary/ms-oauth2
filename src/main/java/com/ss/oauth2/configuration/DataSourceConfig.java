package com.ss.oauth2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author biandra
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.oauth2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
