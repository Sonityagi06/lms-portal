package com.hospital.lms_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.FacultySubject;

public interface FacultySubjectRepository extends JpaRepository<FacultySubject, Long> {

	boolean existsByFacultyIdAndSubjectId(Long facultyId, Long subjectId);
}
