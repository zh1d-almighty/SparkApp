package com.project.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.start.entity.Programs;

public interface ProgramRepository extends JpaRepository<Programs, Long> {
		
	
}
