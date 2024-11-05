package com.project.start.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;
    
    
    
    public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(nullable=false)
    private boolean isEnabled;
    
    @Column(nullable=false)
    private String lastname;
    
    @Column(nullable=true)
    private String AreaOfStudy;
    
    public String getAreaOfStudy() {
		return AreaOfStudy;
	}

	public void setAreaOfStudy(String areaOfStudy) {
		AreaOfStudy = areaOfStudy;
	}

	public String getHighestQualification() {
		return HighestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		HighestQualification = highestQualification;
	}

	public String getCompletedDegree() {
		return CompletedDegree;
	}

	public void setCompletedDegree(String completedDegree) {
		CompletedDegree = completedDegree;
	}

	@Column(nullable=true)
    private String HighestQualification;
    
    
    @Column(nullable=true)
    private String CompletedDegree;
    

    public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(nullable=false, unique=true)
    private String email;

    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable=false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

}
