package com.hospital.lms_portal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class FacultyRegisterDTO {

	@NotBlank
	private String facultyCode;
	
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@NotBlank
	private String phone;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String designation;
	
	@NotBlank
	private String department;

	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
