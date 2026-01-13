package com.hospital.lms_portal.entity;

import java.time.LocalDateTime;

import com.hospital.lms_portal.security.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
		name ="faculty",
		uniqueConstraints = {
				@UniqueConstraint(columnNames ="email")
		})
public class Faculty {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	 @Column(unique = true, nullable = false)
	    private String facultyCode;
	
	@NotBlank(message="Faculty name is required")
	private String name;
	
	@Email(message="Invalid email")
	@NotBlank(message="Email is required")
	private String email;
	
	@NotBlank(message="Phone is required")
	private String phone;
	
	@NotBlank(message="Password is required")
	@Column(name="password_hash")
	private String passwordHash;
	
	@NotBlank(message ="Designation is required")
	private String designation;
	
	@NotBlank(message = "Department is required")
	private String department;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role = Role.FACULTY;
	
	private LocalDateTime createdAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	} 
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getFacultyCode() {
		return facultyCode;
	}

	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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
