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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/admin")
@SecurityRequirement(name="bearerAuth")
public class AdminAuthController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginAdmin(@Valid @RequestBody AdminLoginDTO dto){
		
		String token = adminService.loginAdmin(dto);
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("token", token);
		response.put("role", "ADMIN");
		response.put("userName", dto.getUsername());
		return ResponseEntity.ok(response);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create-faculty")
	public String createFaculty() {
		return "Faculty created";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/students")
	public List<Student> getStudents(@RequestParam(required = false) String branch , @RequestParam(required= false) Integer semester){
		
		return studentService.getAllStudents(branch, semester);
	}
}
