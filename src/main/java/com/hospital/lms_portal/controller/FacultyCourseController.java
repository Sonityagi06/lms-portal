package com.hospital.lms_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.entity.Course;
import com.hospital.lms_portal.service.CourseFacultyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/faculty/courses")
@SecurityRequirement(name="bearerAuth")
public class FacultyCourseController {

	@Autowired
	private CourseFacultyService service;
	
	@PreAuthorize("hasRole('FACULTY')")
	@GetMapping
	public ResponseEntity<List<Course>> getMyCourses(){
		
		return ResponseEntity.ok(service.getFacultyCourses());
	}
}
