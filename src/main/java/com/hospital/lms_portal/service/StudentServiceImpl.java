package com.hospital.lms_portal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

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
	
}
