package com.hospital.lms_portal.service;

import com.hospital.lms_portal.dto.StudentLoginDTO;
import com.hospital.lms_portal.dto.StudentRegisterDTO;
import com.hospital.lms_portal.entity.Student;

public interface StudentService {

	Student registerStudent(StudentRegisterDTO dto);
	
	public String loginStudent(StudentLoginDTO dto);
}
