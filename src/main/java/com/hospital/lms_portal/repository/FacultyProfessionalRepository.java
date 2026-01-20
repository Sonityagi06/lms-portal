package com.hospital.lms_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Faculty;
import com.hospital.lms_portal.entity.FacultyProfessional;

public interface FacultyProfessionalRepository extends JpaRepository<FacultyProfessional , Long> {

	Optional<FacultyProfessional> findByFaculty(Faculty faculty);
}
