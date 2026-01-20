package com.hospital.lms_portal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.FacultyLoginDTO;
import com.hospital.lms_portal.dto.FacultyProfessionalDTO;
import com.hospital.lms_portal.dto.FacultyProfessionalUpdateDTO;
import com.hospital.lms_portal.dto.FacultyRegisterDTO;
import com.hospital.lms_portal.entity.Faculty;
import com.hospital.lms_portal.entity.FacultyProfessional;
import com.hospital.lms_portal.repository.FacultyProfessionalRepository;
import com.hospital.lms_portal.repository.FacultyRepository;
import com.hospital.lms_portal.security.JwtUtil;
import com.hospital.lms_portal.security.Role;

@Service
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	private FacultyRepository facultyRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FacultyProfessionalRepository facultyProRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public Faculty registerFaculty(FacultyRegisterDTO dto) {
		
		facultyRepo.findByFacultyCode(dto.getFacultyCode())
		.ifPresent(f -> { throw new RuntimeException("Faculty already exists");});
		
		Faculty f = new Faculty();
		f.setFacultyCode(dto.getFacultyCode());
		f.setName(dto.getName());
		f.setEmail(dto.getEmail());
		f.setPhone(dto.getPhone());
		f.setDesignation(dto.getDesignation());
		f.setDepartment(dto.getDepartment());
		f.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
		f.setCreatedAt(LocalDateTime.now());
		f.setRole(Role.FACULTY);
		
		return facultyRepo.save(f);
	}

	@Override
	public String loginFaculty(FacultyLoginDTO dto) {
		
		Faculty f = facultyRepo.findByFacultyCode(dto.getFacultyCode())
				.orElseThrow(() -> new RuntimeException("Faculty not found"));
		
		if(!passwordEncoder.matches(dto.getPassword(), f.getPasswordHash())) {
			throw new RuntimeException("Invalid password");
		}
		return jwtUtil.generateToken(f.getFacultyCode(), "FACULTY");
	}



	@Override
	public Faculty getLoggedInFaculty() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String facultyCode = authentication.getName();
		
		
		return facultyRepo.findByFacultyCode(facultyCode).orElseThrow(() -> new RuntimeException("Logged-in faculty not found"));
	}

	
	@Override
	public List<Faculty> getFacultyList(String department) {
		
		if(department == null || department.isBlank()) {
			return facultyRepo.findAll();
		}
		return facultyRepo.findByDepartment(department);
	}

	

	@Override
	public FacultyProfessionalDTO getFacultyProfessionalDTO() {
		
		Faculty faculty = getLoggedInFaculty();
		
		FacultyProfessional prof = facultyProRepo.findByFaculty(faculty)
				.orElse(null);
		return new FacultyProfessionalDTO(faculty, prof);
	}
	
	@Override
	public void saveOrUpdateProfessional(FacultyProfessionalUpdateDTO dto) {
		
		Faculty faculty = getLoggedInFaculty();
		
		FacultyProfessional prof = facultyProRepo.findByFaculty(faculty)
				.orElse(new FacultyProfessional());
		
		prof.setQualification(dto.getQualification());
		prof.setExperienceYears(dto.getExperienceYears());
		prof.setSpecialization(dto.getSpecialization());
		prof.setFaculty(faculty);
		
		facultyProRepo.save(prof);

	}
}
