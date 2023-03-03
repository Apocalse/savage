package com.kaltsit.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class StartLog implements CommandLineRunner {
    @Override
    public void run(String... args) {
        String commandLog = "\n" +
                "  ___ _   _  ___ ___ ___  ___ ___ \n" +
                " / __| | | |/ __/ __/ _ \\/ __/ __|\n" +
                " \\__ \\ |_| | (_| (_|  __/\\__ \\__ \\\n" +
                " |___/\\__,_|\\___\\___\\___||___/___/\n" +
                "                                  \n" +
                "swagger文档: http://localhost:8823/kaltsit/swagger-ui.html\n";
        System.out.println(commandLog);
    }
}
