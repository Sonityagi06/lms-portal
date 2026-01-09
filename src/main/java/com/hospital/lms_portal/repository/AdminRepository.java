package com.hospital.lms_portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.lms_portal.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByEmail(String email);
}
