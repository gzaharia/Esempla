package com.esempla.blog.controller;


import com.esempla.blog.domain.Category;
import com.esempla.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {



    private final CategoryService categoryService;

    @GetMapping("/list")
    public Collection<Category> listCategories(){
        log.debug("Categories size {}", categoryService.listCategories().size());

        return categoryService.listCategories();
    }
}
