package com.esempla.blog.controller;


import com.esempla.blog.domain.Category;
import com.esempla.blog.domain.Comments;
import com.esempla.blog.repository.AppUserRepository;
import com.esempla.blog.repository.CategoryRepository;
import com.esempla.blog.repository.CommentRepository;
import com.esempla.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RequestMapping("comment")
@Controller
@RequiredArgsConstructor
public class CommentController {


    private final PostRepository postRepository;

    private final AppUserRepository appUserRepository;

    private final CommentRepository commentRepository;

    private final CategoryRepository categoryRepository;

    @PostMapping("/save")
    public String save (@RequestParam(value = "postId",required = false) Long postId,
                                @RequestParam(value = "username",required = false) String username,
                                @ModelAttribute Comments comment){



        comment.setCreated_date(new Date());
        comment.setPost(postRepository.getOne(postId));
        comment.setAppUser(appUserRepository.findByUsername(username));
        commentRepository.save(comment);

        return "redirect:/appUser/homePage";
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
