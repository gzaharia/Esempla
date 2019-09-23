package com.esempla.blog.data.repository;

import com.esempla.blog.data.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findByAppUserUsername(String username);

}
