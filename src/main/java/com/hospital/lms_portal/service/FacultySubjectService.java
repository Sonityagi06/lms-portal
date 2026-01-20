package com.hospital.lms_portal.service;

import com.hospital.lms_portal.dto.AssignSubjectDTO;
import com.hospital.lms_portal.dto.FacultySubjectResponseDTO;

public interface FacultySubjectService {

	FacultySubjectResponseDTO assignSubjectToFaculty(AssignSubjectDTO dto);
}
