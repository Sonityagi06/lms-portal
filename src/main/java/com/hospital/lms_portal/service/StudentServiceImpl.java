package com.hospital.lms_portal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.repository.StudentRepository;
import com.hospital.lms_portal.security.JwtUtil;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public Student registerStudent(StudentRegisterDTO dto) {
		
		studentRepository.findByRollNumber(dto.getRollNumber())
		.ifPresent(s -> {
			throw new RuntimeException("Roll number already exists");
		});
		
		Student student = new Student();
		student.setRollNumber(dto.getRollNumber());
		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setPhone(dto.getPhone());
		student.setEnrollYear(dto.getEnrollYear());
		student.setSemester(dto.getSemester());
		student.setBranch(dto.getBranch());
		student.setAddress(dto.getAddress());
		student.setDob(dto.getDob());
		
		student.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
		
		student.setCreatedAt(LocalDateTime.now());
		
		
		return studentRepository.save(student);
	}

	@Override
	public String loginStudent(StudentLoginDTO dto) {
		
		Student student = studentRepository.findByRollNumber(dto.getRollNumber())
				.orElseThrow(() -> new RuntimeException("Student not found"));
		
		if(!passwordEncoder.matches(dto.getPassword(), student.getPasswordHash())) {
			throw new RuntimeException("Invalid password");
		}
		return jwtUtil.generateToken(student.getRollNumber(), "STUDENT");
	}
	
	
	
}
