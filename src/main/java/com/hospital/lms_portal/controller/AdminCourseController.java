package com.hospital.lms_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.CourseCreateDTO;
import com.hospital.lms_portal.dto.CourseResponseDTO;
import com.hospital.lms_portal.service.CourseService;

@RestController
@RequestMapping("/api/admin/course")
public class AdminCourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/create")
	public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseCreateDTO dto){
		
		return ResponseEntity.ok(courseService.createCourse(dto));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
		
		return ResponseEntity.ok(courseService.getAllCourses());
	}
}
