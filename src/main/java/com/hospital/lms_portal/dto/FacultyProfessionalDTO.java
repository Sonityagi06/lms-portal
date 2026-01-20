package com.hospital.lms_portal.dto;

import com.hospital.lms_portal.entity.Faculty;
import com.hospital.lms_portal.entity.FacultyProfessional;

public class FacultyProfessionalDTO {

	private String facultyCode;
	
	private String email;
	
	private String qualification;
	
	private Integer experienceYears;
	
	private String specialization;
	
	public FacultyProfessionalDTO(Faculty faculty, FacultyProfessional prof) {
		this.facultyCode = faculty.getFacultyCode();
		this.email=faculty.getEmail();
		
		if(prof != null) {
			this.qualification=prof.getQualification();
			this.experienceYears = prof.getExperienceYears();
			this.specialization = prof.getSpecialization();
		}
	}

	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
}
