package com.esempla.blog.app.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.esempla.blog.*"})
public class RunAppApi {
    public static void main(String[] args) {
        SpringApplication.run(RunAppApi.class, args);
    }
}
