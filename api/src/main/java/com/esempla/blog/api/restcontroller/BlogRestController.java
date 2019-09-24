package com.esempla.blog.api.restcontroller;

import com.esempla.blog.data.domain.Post;
import com.esempla.blog.data.repository.BlogRepository;
import com.esempla.blog.data.repository.CategoryRepository;
import com.esempla.blog.data.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructora
public class BlogRestController {
    private final BlogRepository blogRepository;

    private final CategoryRepository categoryRepository;

    private final PostRepository postRepository;

    @GetMapping("/post-by-id")
    public ResponseEntity<Post> getPostById(@RequestParam("id") Long id){
        return ResponseEntity.ok(postRepository.findById(id).get());
    }



}
