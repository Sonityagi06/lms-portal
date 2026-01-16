package com.hospital.lms_portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.ApiResponse;
import com.hospital.lms_portal.dto.LoginResponseDTO;
import com.hospital.lms_portal.dto.StudentChangePasswordDTO;
import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentProfileDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.dto.StudentUpdateDTO;
import com.hospital.lms_portal.entity.Student;

import com.hospital.lms_portal.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name="Student APIs", description="Student authentication, profile &password management")
@RestController
@RequestMapping("/api/auth/student")
@SecurityRequirement(name = "bearerAuth")
public class StudentAuthController {

	@Autowired
	private StudentService studentService;
	
	
	//=============REGISTER==========================
	
	@Operation(summary ="Register new student")
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<StudentProfileDTO>> registerStudent(@Valid @RequestBody StudentRegisterDTO dto){
		
		
		Student student = studentService.registerStudent(dto);
		
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(
						true,
						"Student registered successfully",
						new StudentProfileDTO(student)
						));
		
	}
	
	//===================LOGIN==========================
	
	@Operation(summary = "Student login")
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponseDTO>> studentLogin(@Valid @RequestBody StudentLoginDTO dto){
		
		String token = studentService.loginStudent(dto);
		
		
		LoginResponseDTO loginResponse = new LoginResponseDTO(token, "STUDENT", dto.getRollNumber());
		return ResponseEntity.ok(new ApiResponse<>(true,"Login successful",loginResponse));
	}
	
	
	//========================GET PROFILE==================
	
	@Operation(summary ="Get student profile")
	@PreAuthorize("hasRole('STUDENT')")
	@GetMapping("/profile")
	
	public ResponseEntity<ApiResponse<StudentProfileDTO>> getProfile() {
		
		Student student =  studentService.getProfile();
		
		return ResponseEntity.ok(
				new ApiResponse<>(
						true,
						"Profile fetched successfully",
						new StudentProfileDTO(student))
				);
	}
	
	//==================UPDATE PROFILE=================
	
	@Operation(summary = "Update student profile")
	@PreAuthorize("hasRole('STUDENT')")
	@PatchMapping("/profile")
	public ResponseEntity<ApiResponse<StudentProfileDTO>> updateProfile(@Valid
			@RequestBody StudentUpdateDTO dto){
		
		
		Student updatedStudent = studentService.updateProfile(dto);
		
		return ResponseEntity.ok(
				new ApiResponse<>(
						true,
						"Profile updated successfully",
						new StudentProfileDTO(updatedStudent)
						));
	}
	
	
	//=======================CHANGE PASSWORD ========================
	
	
	@Operation(summary = "Change student password")
	@PreAuthorize("hasRole('STUDENT')")
	@PatchMapping("/change-password")
	public ResponseEntity<ApiResponse<Void>> changePassword(@Valid @RequestBody StudentChangePasswordDTO dto){
		
		studentService.changePassword(dto);
		
		return ResponseEntity.ok(new ApiResponse<>(true,
				"Password changed successfully",null)
				);
	}
}
