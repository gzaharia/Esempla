package com.esempla.blog.frontend.controller;

import com.esempla.blog.data.domain.Category;
import com.esempla.blog.data.domain.Comments;
import com.esempla.blog.data.domain.Post;
import com.esempla.blog.data.repository.BlogRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RequestMapping("/blog")
@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogRepository blogRepository;

    private final CategoryRepository categoryRepository;

    private final PostRepository postRepository;


    @GetMapping("/form")
    public String getBlogForm (Model model){

        model.addAttribute("post",new Post());
        model.addAttribute("categories", categoryRepository.findAll());
        return "blogForm";
    }



    @PostMapping("/save")
    public String save (@ModelAttribute Post post, Principal principal){

        post.setCreated_date(new Date());
        post.setBlog(blogRepository.findByAppUserUsername(principal.getName()));
        postRepository.save(post);


        return "redirect:/";
    }
    @PostMapping("/update")
    public String update (@ModelAttribute Post post, Principal principal){

        post.setCreated_date(new Date());
        post.setBlog(blogRepository.findByAppUserUsername(principal.getName()));
        postRepository.save(post);


        return "redirect:/";
    }



    @GetMapping("/update-post")
    public String updatePost (@RequestParam("id") Long id, Model model){
        model.addAttribute("post", postRepository.findById(id));
        model.addAttribute("categories",categoryRepository.findAll());
        return  "blogForm";
    }

    @GetMapping("/delete-post")
    public String deletePost(@RequestParam("id") Long id){
        postRepository.deleteById(id);

        return "redirect:/app-user/home-page";
    }


    @GetMapping("/blog-page")
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
