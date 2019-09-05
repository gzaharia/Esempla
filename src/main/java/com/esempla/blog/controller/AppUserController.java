package com.esempla.blog.controller;

import com.esempla.blog.domain.Comments;
import com.esempla.blog.repository.AppUserRepository;
import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Iterator;

@Controller
@RequestMapping("/appUser")
public class AppUserController {


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/homePage")
    public String homePage(Model model, Principal principal) {




        model.addAttribute("newComment", new Comments());
        model.addAttribute("blogs", postRepository.findAllByBlogAppUserUsername(principal.getName()));
        model.addAttribute("authenticatedUserUsername", principal.getName());

        //model.addAttribute("authenticatedUserUsername",principal.getName());
        return "homePage";
    }

}
