package com.kaltsit.config;

import com.kaltsit.base.LavaSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LavaMybatisPlusConfig {

    /**
     * 自定义的sql注入
     * @return LavaSqlInjector
     */
    @Bean
    public LavaSqlInjector sqlInjector() {
        return new LavaSqlInjector();
    }

}
