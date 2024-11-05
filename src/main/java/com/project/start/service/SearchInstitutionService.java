package com.project.start.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.project.start.entity.Institution;
import com.project.start.repository.SearchInstitutionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchInstitutionService {
	
	 private SearchInstitutionRepository searchInstitutionRepository;
	 
	 public List<Institution> getAllInstitution() {
	        List<Institution> list = searchInstitutionRepository.findAll();
	        return list;
    }
	    
	 public List<Institution> getByKeyword(String keyword) {
	        return searchInstitutionRepository.findByKeyword(keyword);
    }
}
