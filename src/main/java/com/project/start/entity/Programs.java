package com.project.start.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="programs")
public class Programs {
	
	  private static final long serialVersionUID = 1L;

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
