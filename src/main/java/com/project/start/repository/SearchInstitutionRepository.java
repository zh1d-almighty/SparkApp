package com.project.start.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.start.entity.Institution;

@Repository
public interface SearchInstitutionRepository extends JpaRepository<Institution, Integer>{

	
}
