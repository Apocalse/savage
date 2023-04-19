package com.kaltsit.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Data
@Configuration
public class StartLog implements CommandLineRunner {
    @Value("${spring.profiles.active}")
    private String active;

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void run(String... args) {
        String commandLog = "\n" +
                "  ___ _   _  ___ ___ ___  ___ ___ \n" +
                " / __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                " \\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                " |___/\\__,_|\\___\\___\\___||___/___/\n\n" +
                "当前环境: " + active + "\n" +
                "swagger文档: http://localhost:" + port + contextPath + "/swagger-ui.html\n";
        System.out.println(commandLog);
    }
}
