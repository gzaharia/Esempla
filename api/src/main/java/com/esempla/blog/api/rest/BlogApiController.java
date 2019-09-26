package com.esempla.blog.api.rest;

import com.esempla.blog.data.dto.PostDto;
import com.esempla.blog.data.domain.Post;
import com.esempla.blog.data.repository.BlogRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.PostRepository;
import com.esempla.blog.data.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/post")
@AllArgsConstructor

public class BlogApiController {

    private final PostRepository postRepository;

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> t = postRepository.findById(id);
        if(t.isPresent()){
            return ResponseEntity.ok(
                    t.orElseThrow(() -> new RuntimeException("Get post Exception")));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Post>  create(@Valid @RequestBody PostDto postDto) {

        return ResponseEntity.ok(
                postService.save(postDto).orElseThrow(() -> new RuntimeException("Create post Exception")));

    }

    @PutMapping
    public ResponseEntity<Post>  update(@Valid @RequestBody PostDto postDto) {
        log.debug("REST request to update POST : {}", postDto);
        return ResponseEntity.ok(
                postService.save(postDto).orElseThrow(() -> new RuntimeException("Update post Exception")));

    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        log.debug("REST request to delete post with id " + id);
        postRepository.deleteById(id);

        return "The post with id " + id + " deleted";
    }

}
