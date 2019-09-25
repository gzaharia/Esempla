package com.esempla.blog.api.rest;

import com.esempla.blog.data.domain.Post;
import com.esempla.blog.data.repository.BlogRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class BlogRestController {
    private final BlogRepository blogRepository;

    private final CategoryRepository categoryRepository;

    private final PostRepository postRepository;

    @GetMapping("/post-by-id")
    public ResponseEntity<Post> getPostById(@RequestParam("id") Long id) {

        return ResponseEntity.ok(
                postRepository.findById(id).orElseThrow(() -> new RuntimeException("bla bla bla"))
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Post> save(@RequestBody Post post) {

        return ResponseEntity.ok(postRepository.save(post));
    }

    @PostMapping("/update")
    public ResponseEntity<Post> update(@RequestBody Post post) {

        return ResponseEntity.ok(postRepository.save(post));
    }


}
