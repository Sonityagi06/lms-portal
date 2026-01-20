package com.hospital.lms_portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{

	Optional<Faculty> findByFacultyCode(String facultyCode);
	List<Faculty> findByDepartment(String department);
}
