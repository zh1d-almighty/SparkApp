package com.project.start.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="institutions")
@Data
public class Institution {
	
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long institutionid;

	    
	    @Column(nullable=false)
	    private String institutionname;
	    
	    @Column(nullable=false)
	    private String location;
	    
	    @Column(nullable=false)
	    private String description;

		public Long getInstitutionid() {
			return institutionid;
		}

		public void setInstitutionid(Long institutionid) {
			this.institutionid = institutionid;
		}

		public String getInstitutionname() {
			return institutionname;
		}

		public void setInstitutionname(String institutionname) {
			this.institutionname = institutionname;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	    
		
		 @Lob
		    @Column
		    private byte[] Image;

		    public String generateBase64Image() {
		        return Base64.encodeBase64String(this.Image);
		    }

			public byte[] getImage() {
				return Image;
			}

			public void setImage(byte[] image) {
				Image = image;
			}
	    
	    
}
