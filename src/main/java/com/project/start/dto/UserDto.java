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
public class UserDto
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "First Name should not be empty")
    private String name;

 
    private String HighestQualification;

	public String getPassword() {
		return password;
	}
	
    private String AreaOfStudy;
    
    private String CompletedDegree;
    
	public String getHighestQualification() {
		return HighestQualification;
	}


	public void setHighestQualification(String highestQualification) {
		HighestQualification = highestQualification;
	}


	public String getAreaOfStudy() {
		return AreaOfStudy;
	}


	public void setAreaOfStudy(String areaOfStudy) {
		AreaOfStudy = areaOfStudy;
	}


	public String getCompletedDegree() {
		return CompletedDegree;
	}


	public void setCompletedDegree(String completedDegree) {
		CompletedDegree = completedDegree;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Last Name should not be empty")
	private String lastname;
    
    
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    
    
    @NotEmpty(message = "Password should not be empty")
    private String password;
    
    
	public String getEmail() {
		return email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
