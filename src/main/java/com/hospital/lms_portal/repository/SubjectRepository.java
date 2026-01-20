package com.hospital.lms_portal.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	boolean existsBySubjectCode(String subjectCode);
}
