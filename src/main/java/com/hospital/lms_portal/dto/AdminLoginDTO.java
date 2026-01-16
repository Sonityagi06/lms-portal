package com.hospital.lms_portal.dto;

import jakarta.validation.constraints.NotBlank;

public class AdminLoginDTO {

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	

	public AdminLoginDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
