package com.esempla.blog.frontend.controller;

import com.esempla.blog.data.domain.Category;
import com.esempla.blog.data.domain.Comments;
import com.esempla.blog.data.repository.AppUserRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/app-user")
@RequiredArgsConstructor
public class AppUserController {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/home-page")
    public String getHomePage(Model model, Principal principal) {
        if (principal != null) {
            Iterator i = ((UsernamePasswordAuthenticationToken) principal).getAuthorities().iterator();
            GrantedAuthority grantedAuthority = null;
            while (i.hasNext()) {
                grantedAuthority = (GrantedAuthority) i.next();
            }
            model.addAttribute("authenticatedUserRole", grantedAuthority.getAuthority());
        }
        model.addAttribute("newComment", new Comments());
        model.addAttribute("blogs", postRepository.findAllByBlogAppUserUsername(principal.getName()));
        return "homePage";
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
