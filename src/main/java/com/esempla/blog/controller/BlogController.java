package com.esempla.blog.controller;

import com.esempla.blog.domain.Post;
import com.esempla.blog.repository.BlogRepository;
import com.esempla.blog.repository.CategoryRepository;
import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RequestMapping("/blog")
@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/form")
    public String twittFrom (Model model , Principal principal){

        model.addAttribute("post",new Post());
        model.addAttribute("authenticatedUserUsername", principal.getName());
        model.addAttribute("categories", categoryRepository.findAll());
        return "blogForm";
    }



    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate (@ModelAttribute Post post, Model model , Principal principal){

        post.setCreated_date(new Date());
        post.setBlog(blogRepository.findByAppUserUsername(principal.getName()));
        //post.setCategory(categoryRepository.findById(2l).get());
        postRepository.save(post);
        model.addAttribute("authenticatedUserUsername", principal.getName());


        return "redirect:/index";
    }



    @GetMapping("/updatePost")
    public String updatePost (@RequestParam("id") Long id, Model model , Principal principal){
        model.addAttribute("post", postRepository.findById(id));
        model.addAttribute("authenticatedUserUsername", principal.getName());
        return  "blogForm";
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam("id") Long id, Model model, Principal principal){
        postRepository.deleteById(id);
        model.addAttribute("authenticatedUserUsername", principal.getName());

        return "redirect:/appUser/homePage";
    }


}
