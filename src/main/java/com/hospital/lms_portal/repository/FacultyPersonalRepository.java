package com.hospital.lms_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.FacultyPersonal;

public interface FacultyPersonalRepository extends JpaRepository<FacultyPersonal, Long>{

	Optional<FacultyPersonal> findByEmail(String email);
}
