package com.esempla.blog.repository;

import com.esempla.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findByAppUserUsername(String username);

}