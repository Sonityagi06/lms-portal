package com.hospital.lms_portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="faculty_professional")
public class FacultyProfessional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String qualification;
	
	private Integer experienceYears;
	
	private String specialization;
	
	@OneToOne
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;
	
	
	public FacultyProfessional() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public Integer getExperienceYears() {
		return experienceYears;
	}


	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}


	public String getSpecialization() {
		return specialization;
	}


	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


	public Faculty getFaculty() {
		return faculty;
	}


	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	
}
