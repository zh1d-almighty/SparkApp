package com.project.start.dto;



import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramsDto {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long programid;

	    @Column(nullable=false)
	    private String program;
	    
	    private String IstitutionName;

		public Long getProgramid() {
			return programid;
		}

		public void setProgramid(Long programid) {
			this.programid = programid;
		}

		public String getProgram() {
			return program;
		}

		public void setProgram(String program) {
			this.program = program;
		}

		public String getIstitutionName() {
			return IstitutionName;
		}

		public void setIstitutionName(String istitutionName) {
			IstitutionName = istitutionName;
		}
	
	

}
