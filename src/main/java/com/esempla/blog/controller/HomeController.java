package com.esempla.blog.controller;

import com.esempla.blog.domain.Comments;
import com.esempla.blog.domain.Post;
import com.esempla.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/index")
public class HomeController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String getHome(Model model, Principal principal){

        if(principal != null){

            Iterator i = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().iterator();
            GrantedAuthority grantedAuthority = null;
            while (i.hasNext()){
                grantedAuthority = (GrantedAuthority) i.next();
            }
            model.addAttribute("authenticatedUserRole", grantedAuthority.getAuthority());

        }
        model.addAttribute("blogs",postRepository.findAll());
        model.addAttribute("newComment", new Comments());
        model.addAttribute("authenticatedUserUsername", principal != null ? principal.getName() : "Guest");

        return "index";
    }


    @GetMapping("/message")
    public String getMessage(@RequestParam("categoryId") Long categoryId, Model model, Principal principal){

        Iterator i = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().iterator();
        GrantedAuthority grantedAuthority = null;
        while (i.hasNext()){
            grantedAuthority = (GrantedAuthority) i.next();
        }

        model.addAttribute("blogs",postRepository.findAllByCategoryId(categoryId));
        model.addAttribute("newComment", new Comments());
        model.addAttribute("authenticatedUserUsername", principal.getName());
        model.addAttribute("authenticatedUserRole", grantedAuthority.getAuthority());

        return "showPostsByCategory";

    }


}
