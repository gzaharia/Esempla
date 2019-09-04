package com.esempla.blog.controller;

import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class HomeController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String getHome(){
        return "index";
    }


    @GetMapping("/message")
    @ResponseBody
    public String getMessage(){
        postRepository.findAllByCategoryId(1l);

        return "Hi there!";
    }

    @GetMapping("/message2")
    @ResponseBody
    public String getMessage2(){
        return "Hi there2!";
    }

}
