package com.project.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.start.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

