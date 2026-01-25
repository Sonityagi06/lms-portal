package com.hospital.lms_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
	boolean existsByCourseCode(String courseCode);

}
