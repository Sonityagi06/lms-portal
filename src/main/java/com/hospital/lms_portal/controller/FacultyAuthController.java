package com.hospital.lms_portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.lms_portal.dto.FacultyLoginDTO;
import com.hospital.lms_portal.dto.FacultyProfessionalDTO;
import com.hospital.lms_portal.dto.FacultyProfessionalUpdateDTO;
import com.hospital.lms_portal.dto.FacultyRegisterDTO;
import com.hospital.lms_portal.entity.Faculty;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.repository.StudentRepository;
import com.hospital.lms_portal.service.FacultyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Faculty APIs", description="Faculty authentication and student access APIs")
@RestController
@RequestMapping("/api/auth/faculty")
@SecurityRequirement(name = "bearerAuth")
public class FacultyAuthController {

	@Autowired 
	private StudentRepository studentRepo;
	
	@Autowired
	private FacultyService facultyService;
	
	@Operation(summary = "Register new faculty")
	@PostMapping("/register")
	public ResponseEntity<?> registerFaculty(@Valid @RequestBody FacultyRegisterDTO dto){
		Faculty faculty = facultyService.registerFaculty(dto);
		
		Map<String , Object> response = new HashMap<>();
		response.put("facultyCode", faculty.getFacultyCode());
		response.put("email", faculty.getEmail());
		response.put("role", "FACULTY");
		response.put("message", "Faculty registered successfully");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Faculty login")
	@PostMapping("/login")
	public ResponseEntity<?> loginFaculty(@Valid @RequestBody FacultyLoginDTO dto){
		String token = facultyService.loginFaculty(dto);
		
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("role", "FACULTY");
		response.put("facultyCode", dto.getFacultyCode());
		
		
		return ResponseEntity.ok(response);
	}
	
	@Operation(summary = "Get all students (Faculty only)")
	@PreAuthorize("hasRole('FACULTY')")
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return studentRepo.findAll();
	}
	
	
	@PreAuthorize("hasRole('FACULTY')")
	@GetMapping("/profile")
	public ResponseEntity<FacultyProfessionalDTO> getProfile(){
		
		return ResponseEntity.ok(facultyService.getFacultyProfessionalDTO());
	}
	
	
	@PreAuthorize("hasRole('FACULTY')")
	@PatchMapping("/professional")
	public ResponseEntity<String> saveProfessional(
			@Valid
			@RequestBody FacultyProfessionalUpdateDTO dto){
		
		facultyService.saveOrUpdateProfessional(dto);
		return ResponseEntity.ok("Professional profile updated");
	}
	

}
