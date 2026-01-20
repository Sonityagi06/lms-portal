package com.hospital.lms_portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.AssignSubjectDTO;
import com.hospital.lms_portal.dto.FacultySubjectResponseDTO;
import com.hospital.lms_portal.service.FacultySubjectService;

@RestController
@RequestMapping("/api/admin/faculty-subject")
public class AdminFacultySubjectController {

	@Autowired
	private FacultySubjectService facultySubjectService;
	
	@PostMapping("/assign")
	public ResponseEntity<FacultySubjectResponseDTO> assignSubject(
			@RequestBody AssignSubjectDTO dto){
		
		return ResponseEntity.ok(facultySubjectService.assignSubjectToFaculty(dto));
	}
}
