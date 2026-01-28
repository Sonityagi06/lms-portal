package com.hospital.lms_portal.service;

import com.hospital.lms_portal.dto.EnrollmentRequestDTO;
import com.hospital.lms_portal.dto.EnrollmentResponseDTO;

public interface EnrollmentService {

	EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto);
}
