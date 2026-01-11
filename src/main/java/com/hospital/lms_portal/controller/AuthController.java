package com.hospital.lms_portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/student")
public class AuthController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRegisterDTO dto){
		
		System.out.println("DTO DATA = " + dto);
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
}
