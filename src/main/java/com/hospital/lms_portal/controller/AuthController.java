package com.hospital.lms_portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.service.StudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/student")
public class AuthController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRegisterDTO dto){
		
		Student student = studentService.registerStudent(dto);
		
		Map<String, Object> response = new HashMap<>();
		response.put("studentId", student.getId());
		response.put("roll_number", student.getRollNumber());
		response.put("message", "Student registered Successfully");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
