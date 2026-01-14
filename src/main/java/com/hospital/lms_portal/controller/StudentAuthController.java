package com.hospital.lms_portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.StudentChangePasswordDTO;
import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.dto.StudentUpdateDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.security.JwtUtil;
import com.hospital.lms_portal.service.StudentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/student")
@SecurityRequirement(name = "bearerAuth")
public class StudentAuthController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRegisterDTO dto){
		
		
		Student student = studentService.registerStudent(dto);
		
		Map<String, Object> response = new HashMap<>();
		response.put("studentId", student.getId());
		response.put("roll_number", student.getRollNumber());
		response.put("message", "Student registered Successfully");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> studentLogin(@Valid @RequestBody StudentLoginDTO dto){
		
		String token = studentService.loginStudent(dto);
		
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("role", "STUDENT");
		response.put("roll_number", dto.getRollNumber());
		
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@GetMapping("/profile")
	public Student getProfile() {
		
		return studentService.getProfile();
	}
	
	@PatchMapping("/profile")
	public ResponseEntity<Student> updateProfile(@RequestHeader("Authorization") String authHeader,
			@RequestBody StudentUpdateDTO dto){
		
		String token = authHeader.substring(7);
		String rollNumber = jwtUtil.extractUsername(token);
		
		Student updatedStudent = studentService.updateProfile(rollNumber, dto);
		
		return ResponseEntity.ok(updatedStudent);
	}
	
	@PreAuthorize("hasRole('STUDENT')")
	@PatchMapping("/change-password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody StudentChangePasswordDTO dto){
		
		studentService.changePassword(dto);
		
		return ResponseEntity.ok(Map.of("message","Password changed successfully"));
	}
}
