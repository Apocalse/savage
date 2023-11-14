package com.kaltsit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "es.datasource")
@Data
public class ESConfig {
    private String db;

    private String scheme;

    private boolean useES;

    private String hosts;

    private String username;

    private String password;
}
