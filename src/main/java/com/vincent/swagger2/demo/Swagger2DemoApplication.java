package com.vincent.swagger2.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableRetry
@EnableAdminServer
@SpringBootApplication
public class Swagger2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Swagger2DemoApplication.class, args);
    }

}
