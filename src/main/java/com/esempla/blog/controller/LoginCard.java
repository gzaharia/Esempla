package com.esempla.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginCard")
public class LoginCard {
    @GetMapping
    public String getLoginPage(){
        return "loginCard";
    }
}
