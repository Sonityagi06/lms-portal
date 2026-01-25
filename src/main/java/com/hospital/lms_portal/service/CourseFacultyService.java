package com.hospital.lms_portal.service;

import java.util.List;

import com.hospital.lms_portal.dto.CourseFacultyAssignDTO;
import com.hospital.lms_portal.entity.Course;

public interface CourseFacultyService {

	void assignFacultyToCourse(CourseFacultyAssignDTO dto);
	
	List<Course> getFacultyCourses();
}
