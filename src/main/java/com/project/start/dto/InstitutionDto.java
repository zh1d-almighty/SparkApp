package com.project.start.dto;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long institutionid;
    
    public Long getInstitutionid() {
		return institutionid;
	}

	public void setInstitutionid(Long institutionid) {
		this.institutionid = institutionid;
	}

	public String getInstitutionname() {
		return Institutionname;
	}

	public void setInstitutionname(String institutionname) {
		Institutionname = institutionname;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	@NotEmpty(message = "Institution Name should not be empty")
    private String Institutionname;

    @NotEmpty(message = "Location Name should not be empty")
    private String Location;
    
    

}
