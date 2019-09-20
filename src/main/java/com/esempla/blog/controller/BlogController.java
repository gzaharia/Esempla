package com.esempla.blog.controller;

import com.esempla.blog.domain.Category;
import com.esempla.blog.domain.Comments;
import com.esempla.blog.domain.Post;
import com.esempla.blog.repository.BlogRepository;
import com.esempla.blog.repository.CategoryRepository;
import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    public String blogForm (Model model , Principal principal){

        model.addAttribute("post",new Post());
        model.addAttribute("categories", categoryRepository.findAll());
        return "blogForm";
    }



    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate (@ModelAttribute Post post, Model model , Principal principal){

        post.setCreated_date(new Date());
        post.setBlog(blogRepository.findByAppUserUsername(principal.getName()));
        postRepository.save(post);


        return "redirect:/index";
    }



    @GetMapping("/updatePost")
    public String updatePost (@RequestParam("id") Long id, Model model , Principal principal){
        model.addAttribute("post", postRepository.findById(id));
        model.addAttribute("categories",categoryRepository.findAll());
        return  "blogForm";
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam("id") Long id, Model model, Principal principal){
        postRepository.deleteById(id);

        return "redirect:/appUser/homePage";
    }


    @GetMapping("/blogPage")
    public String getBlogById(@RequestParam("blogId") Long blogId, Model model){

        model.addAttribute("blog",postRepository.findById(blogId).get());

        return "blogPage";

    }


    @ModelAttribute("newComment")
    public Comments getNewComment(){
        return new Comments();
    }


    @ModelAttribute("allCategories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @ModelAttribute("authenticatedUserUsername")
    public String getAuthenticatedUserUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails ?
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername() : "Guest";

    }

}
