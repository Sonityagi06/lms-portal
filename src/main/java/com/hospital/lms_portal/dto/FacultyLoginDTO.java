package com.hospital.lms_portal.dto;

import jakarta.validation.constraints.NotBlank;

public class FacultyLoginDTO {

	@NotBlank
	private String facultyCode;
	
	@NotBlank
	private String password;

	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
