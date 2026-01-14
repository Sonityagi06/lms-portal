package com.hospital.lms_portal.service;

import java.util.List;

import com.hospital.lms_portal.dto.StudentChangePasswordDTO;
import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.dto.StudentUpdateDTO;
import com.hospital.lms_portal.entity.Student;

public interface StudentService {

	Student registerStudent(StudentRegisterDTO dto);
	
	public String loginStudent(StudentLoginDTO dto);
	
	Student getProfile();
	
	Student updateProfile(String rollNumber, StudentUpdateDTO dto);
	
	void changePassword(StudentChangePasswordDTO dto);
	
	List<Student> getAllStudents(String branch, Integer semester);
	
}
