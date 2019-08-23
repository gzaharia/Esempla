package com.esempla.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
    @GetMapping("/loginCard")
    public String singIn(){
        return "loginCard";
    }
}
