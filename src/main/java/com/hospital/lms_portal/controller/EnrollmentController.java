package com.hospital.lms_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.EnrollmentRequestDTO;
import com.hospital.lms_portal.dto.EnrollmentResponseDTO;
import com.hospital.lms_portal.service.EnrollmentService;

@RestController
@RequestMapping("/api/student/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping
	public ResponseEntity<EnrollmentResponseDTO> enrollStudent(@RequestBody EnrollmentRequestDTO dto){
		
		return ResponseEntity.ok(enrollmentService.enrollStudent(dto));
	}
}
