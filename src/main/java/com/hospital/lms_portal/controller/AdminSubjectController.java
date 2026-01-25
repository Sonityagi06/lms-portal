package com.hospital.lms_portal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.SubjectCreateDTO;
import com.hospital.lms_portal.dto.SubjectResponseDTO;
import com.hospital.lms_portal.service.SubjectService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/subjects")
@RequiredArgsConstructor
@SecurityRequirement(name="bearerAuth")
@PreAuthorize("hasRole('ADMIN')") 
public class AdminSubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/create")
	public ResponseEntity<SubjectResponseDTO> createSubject(
			@RequestBody SubjectCreateDTO dto){
		
		return ResponseEntity.ok(subjectService.createSubject(dto));
	}	
	
	@GetMapping("/get")
	public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects(){
		
		return ResponseEntity.ok(subjectService.getAllSubjects());
	}
}
	
