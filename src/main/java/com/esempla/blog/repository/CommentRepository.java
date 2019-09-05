package com.esempla.blog.repository;

import com.esempla.blog.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    //List<Comments> findAllByAppUserId();
}

