package com.esempla.blog.repository;

import com.esempla.blog.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
