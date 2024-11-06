package com.project.start.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.start.dto.ProgramsDto;
import com.project.start.entity.Programs;
import com.project.start.repository.ProgramRepository;
import com.project.start.service.ProgramService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImp implements ProgramService {
	
	@Autowired
	 private ProgramRepository programRepository;
	 
	 @Override
	    public List<ProgramsDto> findAllPrograms() {
	        List<Programs> programs = programRepository.findAll();
	        return programs.stream().map((program) -> convertEntityToDto(program))
	                .collect(Collectors.toList());
	    }

	 private ProgramsDto convertEntityToDto(Programs program){
		 
		 ProgramsDto programDto = new ProgramsDto();
	       
		 programDto.setIstitutionName(program.getIstitutionName());
		 programDto.setProgram(program.getProgram());
    
        
        return programDto;
	}
}
