package com.esempla.blog.data.service;

import com.esempla.blog.data.domain.Category;
import com.esempla.blog.data.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public void save(Category category) {
        log.debug("Request to save category '{}'", repository.save(category));
    }
}
