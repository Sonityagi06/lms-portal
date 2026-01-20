package com.hospital.lms_portal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class FacultyProfessionalUpdateDTO {

	@NotBlank(message = "Qualification is required")
	private String qualification;
	
	@Min(value=0, message = "Experience must be 0 or more")
	private Integer experienceYears;
	
	@NotBlank(message = "Specialization is required")
	private String specialization;

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
