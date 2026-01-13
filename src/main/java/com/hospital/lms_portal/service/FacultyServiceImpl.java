package com.hospital.lms_portal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.FacultyLoginDTO;
import com.hospital.lms_portal.dto.FacultyRegisterDTO;
import com.hospital.lms_portal.entity.Faculty;
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

}
