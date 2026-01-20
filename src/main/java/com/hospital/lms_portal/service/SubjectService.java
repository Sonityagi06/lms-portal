package com.hospital.lms_portal.service;

import java.util.List;

import com.hospital.lms_portal.dto.SubjectCreateDTO;
import com.hospital.lms_portal.dto.SubjectResponseDTO;

public interface SubjectService {

	SubjectResponseDTO createSubject(SubjectCreateDTO dto);
	List<SubjectResponseDTO> getAllSubjects();
}
