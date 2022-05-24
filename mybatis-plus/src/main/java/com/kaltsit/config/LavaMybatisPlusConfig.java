package com.kaltsit.config;

import com.kaltsit.base.LavaSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LavaMybatisPlusConfig {

    @Bean
    public LavaSqlInjector sqlInjector() {
        return new LavaSqlInjector();
    }

}
