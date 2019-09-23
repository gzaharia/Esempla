package com.esempla.blog.data.repository;

import com.esempla.blog.data.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByCategoryId(Long id);
    List<Post> findAllByBlogAppUserId (Long id);
    List<Post> findAllByBlogAppUserUsername (String username);
    Optional<Post> findById (Long id);

}
