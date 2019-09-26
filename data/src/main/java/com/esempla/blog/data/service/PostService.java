package com.esempla.blog.data.service;

import com.esempla.blog.data.domain.Post;
import com.esempla.blog.data.dto.PostDto;
import com.esempla.blog.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> save(PostDto postDto){

        log.debug("Request to save post for blog with id '{}'", postDto.getBlogId());

        Post post = Post.builder()
                .comments(new ArrayList<>())
                .build();

        return Optional.of(postRepository.save(post));
    }
}
