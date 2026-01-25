package com.hospital.lms_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.CourseFacultyAssignDTO;
import com.hospital.lms_portal.service.CourseFacultyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/admin/course-faculty")
@SecurityRequirement(name="bearerAuth")
public class CourseFacultyAdminController {

	@Autowired
	private CourseFacultyService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/assign")
	public ResponseEntity<String> assignFaculty(
			@RequestBody CourseFacultyAssignDTO dto){
		
		service.assignFacultyToCourse(dto);
		return ResponseEntity.ok("Faculty assigned to course successfully");
	}
}
