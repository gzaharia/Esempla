package com.esempla.blog.controller;

import com.esempla.blog.domain.Comments;
import com.esempla.blog.domain.Post;
import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/index")
public class HomeController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("blogs",postRepository.findAll());
        return "index";
    }


    @GetMapping("/message")
    public String getMessage(@RequestParam("categoryId") Long categoryId, Model model){

        model.addAttribute("blogs",postRepository.findAllByCategoryId(categoryId));
        List<Post> postList = postRepository.findAllByCategoryId(categoryId);
        Comments comments = postList.get(0).getComments().get(0);
     return "showPostsByCategory";

    }

    @GetMapping("/message2")
    @ResponseBody
    public List<Post> getMessage2(@RequestParam("userId") Long userId){
        return postRepository.findAllByBlogAppUserId(userId);
    }

}
