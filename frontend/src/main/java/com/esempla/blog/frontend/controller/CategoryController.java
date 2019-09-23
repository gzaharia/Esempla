package com.esempla.blog.frontend.controller;


import com.esempla.blog.data.domain.Category;
import com.esempla.blog.data.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/form")
    public String createCategoryForm (Model model){
        model.addAttribute("category",new Category());
        return "categoryForm";
    }


    @PostMapping("/save")
    public String save (@ModelAttribute Category category){


        categoryRepository.save(category);

        return "redirect:/index";
    }

    @PostMapping("/update")
    public String update (@ModelAttribute Category category){


        categoryRepository.save(category);

        return "redirect:/index";
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
