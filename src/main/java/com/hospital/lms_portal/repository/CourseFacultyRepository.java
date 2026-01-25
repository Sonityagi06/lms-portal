package com.hospital.lms_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Course;
import com.hospital.lms_portal.entity.CourseFaculty;
import com.hospital.lms_portal.entity.Faculty;

public interface CourseFacultyRepository extends JpaRepository<CourseFaculty, Long>{

	List<CourseFaculty> findByFaculty(Faculty faculty);
	
	boolean existsByCourseAndFaculty(Course course, Faculty faculty);
}
