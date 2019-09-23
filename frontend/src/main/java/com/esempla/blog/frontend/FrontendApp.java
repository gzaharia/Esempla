package com.esempla.blog.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.esempla.blog.*"})
public class FrontendApp {
    public static void main(String[] args) {
        SpringApplication.run(FrontendApp.class, args);
    }
}
