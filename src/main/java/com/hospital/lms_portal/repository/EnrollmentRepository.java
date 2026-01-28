package com.hospital.lms_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	
	Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

}
