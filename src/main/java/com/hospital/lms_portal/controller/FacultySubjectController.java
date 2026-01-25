package com.hospital.lms_portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.SubjectResponseDTO;
import com.hospital.lms_portal.service.FacultySubjectService;

@RestController
@RequestMapping("/api/faculty/subjects")
public class FacultySubjectController {

	@Autowired
	private FacultySubjectService facultySubjectService;
	
	
	@GetMapping("/my")
	public ResponseEntity<List<SubjectResponseDTO>> getMySubjects(){
		
		return ResponseEntity.ok(facultySubjectService.getMySubjects());
	}
}
