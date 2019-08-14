package com.esempla.blog.service;

import com.esempla.blog.domain.Category;
import com.esempla.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Collection<Category> listCategories() {
        log.debug("Request to get all categories");
        return repository.findAll();
    }

    public void save(Category category) {
        log.debug("Request to save category '{}'", repository.save(category));
    }
}
