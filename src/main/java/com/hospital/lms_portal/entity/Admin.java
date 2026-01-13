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
		name="admins",
		uniqueConstraints = {
				@UniqueConstraint(columnNames="email")
		})
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @Column(unique = true)
	@NotBlank(message="Admin name is required")
	private String username;
	
	@Email(message="Invalid email")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message="Password is required")
	@Column(name="password_hash")
	private String passwordHash;

	@Enumerated(EnumType.STRING)
	@Column(nullable =false)
	private Role role = Role.ADMIN;
	
	 private LocalDateTime createdAt;
	 
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	
}
