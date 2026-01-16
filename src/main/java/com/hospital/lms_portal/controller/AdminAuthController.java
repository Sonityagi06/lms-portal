package com.hospital.lms_portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.AdminLoginDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.service.AdminService;
import com.hospital.lms_portal.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Admin APIs",description = "Admin authentication and administrative operations")
@RestController
@RequestMapping("/api/auth/admin")
@SecurityRequirement(name="bearerAuth")
public class AdminAuthController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private StudentService studentService;
	
	@Operation(summary = "Admin Login")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description="Login successful"),
		@ApiResponse(responseCode ="401" , description = "Invalid credentials")
	})
	@PostMapping("/login")
	public ResponseEntity<?> loginAdmin(@Valid @RequestBody AdminLoginDTO dto){
		
		String token = adminService.loginAdmin(dto);
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("token", token);
		response.put("role", "ADMIN");
		response.put("userName", dto.getUsername());
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary="Create faculty (Admin only)")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create-faculty")
	public String createFaculty() {
		return "Faculty created";
	}
	
	@Operation(
			summary = "Get students with optional filters",
			description = "Filter students by branch and semester")
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/students")
	public List<Student> getStudents(@RequestParam(required = false) String branch , @RequestParam(required= false) Integer semester){
		
		return studentService.getAllStudents(branch, semester);
	}
}
