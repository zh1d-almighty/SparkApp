package com.project.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.start.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
