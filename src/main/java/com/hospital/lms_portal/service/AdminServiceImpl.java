package com.hospital.lms_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.AdminLoginDTO;
import com.hospital.lms_portal.entity.Admin;
import com.hospital.lms_portal.repository.AdminRepository;
import com.hospital.lms_portal.security.JwtUtil;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public String loginAdmin(AdminLoginDTO dto) {
		Admin admin = adminRepo.findByUsername(dto.getUsername())
				.orElseThrow(() -> new RuntimeException("Admin not found"));
		
		
		if(!passwordEncoder.matches(dto.getPassword(), admin.getPasswordHash())) {
			throw new RuntimeException("Invalid password");
		}
		return jwtUtil.generateToken(admin.getUserName(), "ADMIN");
	}

}
