package com.hospital.lms_portal.service;

import java.util.List;

import com.hospital.lms_portal.dto.CourseCreateDTO;
import com.hospital.lms_portal.dto.CourseResponseDTO;

public interface CourseService {

	CourseResponseDTO createCourse(CourseCreateDTO dto);
	
	List<CourseResponseDTO> getAllCourses();
}
