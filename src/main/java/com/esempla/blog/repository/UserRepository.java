package com.esempla.blog.repository;

import com.esempla.blog.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
