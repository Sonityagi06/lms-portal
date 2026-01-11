package com.hospital.lms_portal.dto;

import jakarta.validation.constraints.NotBlank;

public class StudentLoginDTO {

	@NotBlank
	private String rollNumber;
	
	@NotBlank
	private String password;

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
