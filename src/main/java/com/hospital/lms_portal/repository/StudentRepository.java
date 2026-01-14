package com.hospital.lms_portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByEmail(String email);
	Optional<Student> findByRollNumber(String rollNumber);
	
	List<Student> findByBranch(String branch);
	
	List<Student> findBySemester(Integer semester);
	
	List<Student> findByBranchAndSemester(String branch, Integer semester);
}
