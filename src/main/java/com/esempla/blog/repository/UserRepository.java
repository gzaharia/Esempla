package com.esempla.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<User,Long> {
    com.esempla.blog.domain.User findByUsername(String username);
}
