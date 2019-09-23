package com.esempla.blog.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.esempla.blog.*"})
public class RunApp {
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }
}
