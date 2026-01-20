package com.hospital.lms_portal.service;


import java.util.List;

import com.hospital.lms_portal.dto.FacultyLoginDTO;
import com.hospital.lms_portal.dto.FacultyProfessionalDTO;
import com.hospital.lms_portal.dto.FacultyProfessionalUpdateDTO;
import com.hospital.lms_portal.dto.FacultyRegisterDTO;
import com.hospital.lms_portal.entity.Faculty;

public interface FacultyService {

	Faculty registerFaculty(FacultyRegisterDTO dto);
	String loginFaculty(FacultyLoginDTO dto);
	
	Faculty getLoggedInFaculty();
	FacultyProfessionalDTO getFacultyProfessionalDTO();
	
	public void saveOrUpdateProfessional(FacultyProfessionalUpdateDTO dto);
	List<Faculty> getFacultyList(String department);
}
