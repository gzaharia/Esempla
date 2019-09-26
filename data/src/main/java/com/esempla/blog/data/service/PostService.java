package com.esempla.blog.data.service;

import com.esempla.blog.data.domain.Category;
import com.esempla.blog.data.domain.Post;
import com.esempla.blog.data.dto.PostDto;
import com.esempla.blog.data.repository.BlogRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    public Optional<PostDto> save(PostDto postDto){

        log.debug("Request to save post for blog with id '{}'", postDto.getBlogId());

        Post post = Post.builder()
                .name(postDto.getName())
                .category(categoryRepository.findById(postDto.getCategoryId()).get())
                .content(postDto.getContent())
                .created_date(postDto.getCreatedDate())
                .blog(blogRepository.findById(postDto.getBlogId()).get())
                .comments(new ArrayList<>())
                .build();

        return postRepository.save(post) != null ? Optional.of(postDto): Optional.empty();

    }
}
