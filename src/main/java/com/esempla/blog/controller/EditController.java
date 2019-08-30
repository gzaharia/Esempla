package com.esempla.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/editPost")
public class EditController {
    @GetMapping()
    public String getEditPostPage() {
        return "editPost";
    }
}
