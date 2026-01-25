package com.hospital.lms_portal.service;

import java.util.List;

import com.hospital.lms_portal.dto.AssignSubjectDTO;
import com.hospital.lms_portal.dto.FacultySubjectResponseDTO;
import com.hospital.lms_portal.dto.SubjectResponseDTO;

public interface FacultySubjectService {

	FacultySubjectResponseDTO assignSubjectToFaculty(AssignSubjectDTO dto);
	
	List<SubjectResponseDTO> getMySubjects();
}
