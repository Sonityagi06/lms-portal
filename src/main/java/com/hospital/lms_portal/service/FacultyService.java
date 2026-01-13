package com.hospital.lms_portal.service;


import com.hospital.lms_portal.dto.FacultyLoginDTO;
import com.hospital.lms_portal.dto.FacultyRegisterDTO;
import com.hospital.lms_portal.entity.Faculty;

public interface FacultyService {

	Faculty registerFaculty(FacultyRegisterDTO dto);
	String loginFaculty(FacultyLoginDTO dto);
}
