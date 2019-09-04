package com.esempla.blog.repository;

import com.esempla.blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByCategoryId(Long id);

}
