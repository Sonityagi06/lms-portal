package com.hospital.lms_portal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.StudentChangePasswordDTO;
import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.dto.StudentUpdateDTO;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.exception.InvalidCredentialsException;
import com.hospital.lms_portal.exception.StudentNotFoundException;
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
			throw new StudentNotFoundException("Roll number already exists");
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
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));
		
		if(!passwordEncoder.matches(dto.getPassword(), student.getPasswordHash())) {
			throw new InvalidCredentialsException("Invalid password");
		}
		return jwtUtil.generateToken(student.getRollNumber(), "STUDENT");
	}
	
	@Override
	public Student getProfile() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated()) {
			throw new RuntimeException("Unauthorized");
		}
		
		String rollNumber = authentication.getName();
		
		return studentRepository.findByRollNumber(rollNumber)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));
		
	}

	@Override
	public Student updateProfile(StudentUpdateDTO dto) {
		
		String rollNumber = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		Student student = studentRepository.findByRollNumber(rollNumber)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));
		
		if(dto.getName() !=null)
			student.setName(dto.getName());
		
		if(dto.getEmail()!=null)
			student.setEmail(dto.getEmail());
		
		if(dto.getPhone() != null)
			student.setPhone(dto.getPhone());
		
		if(dto.getAddress()!=null)
			student.setAddress(dto.getAddress());
		
		if(dto.getSemester()!=null)
			student.setSemester(dto.getSemester());
		
		if(dto.getBranch()!=null)
			student.setBranch(dto.getBranch());
		
		if(dto.getDob()!=null)
			student.setDob(dto.getDob());
		
		return studentRepository.save(student);
	}

	@Override
	public void changePassword(StudentChangePasswordDTO dto) {
		
		String rollNumber = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		Student student = studentRepository.findByRollNumber(rollNumber)
				.orElseThrow(() -> new StudentNotFoundException("Student not found"));
		
		if(!passwordEncoder.matches(dto.getOldPassword(), student.getPasswordHash())) {
			throw new InvalidCredentialsException("Old password is incorrect");
		}
		
		student.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
		
		studentRepository.save(student);
		
	}

	@Override
	public List<Student> getAllStudents(String branch, Integer semester) {
		
		if(branch != null && semester != null) {
			return studentRepository.findByBranchAndSemester(branch, semester);
		}
		
		if(branch != null) {
			return studentRepository.findByBranch(branch);
		}
		
		if(semester != null) {
			return studentRepository.findBySemester(semester);
		}
		
		return studentRepository.findAll();
	}
	
	
	
}
