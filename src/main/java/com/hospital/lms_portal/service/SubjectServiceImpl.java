package com.hospital.lms_portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.SubjectCreateDTO;
import com.hospital.lms_portal.dto.SubjectResponseDTO;
import com.hospital.lms_portal.entity.Subject;
import com.hospital.lms_portal.repository.SubjectRepository;


@Service

public class SubjectServiceImpl implements SubjectService{

	
	@Autowired
	private  SubjectRepository subjectRepository;
	
	@Override
	public SubjectResponseDTO createSubject(SubjectCreateDTO dto) {
		
		if(subjectRepository.existsBySubjectCode(dto.getSubjectCode())) {
			
			throw new RuntimeException("Subject code already exists");
		}
		
		Subject subject = new Subject();
		subject.setSubjectCode(dto.getSubjectCode());
		subject.setSubjectName(dto.getSubjectName());
		subject.setDescription(dto.getDescription());
		subject.setActive(true);
		
		Subject savedSubject = subjectRepository.save(subject);
		
		SubjectResponseDTO response = new SubjectResponseDTO();
		response.setId(savedSubject.getId());
		response.setSubjectCode(savedSubject.getSubjectCode());
		response.setSubjectName(savedSubject.getSubjectName());
		response.setActive(savedSubject.getActive());
		
		
		return response;
	}

	@Override
	public List<SubjectResponseDTO> getAllSubjects() {
		
		return subjectRepository.findAll()
				.stream()
				.map(subject -> {
					SubjectResponseDTO dto = new SubjectResponseDTO();
					dto.setId(subject.getId());
					dto.setSubjectCode(subject.getSubjectCode());
					dto.setSubjectName(subject.getSubjectName());
					dto.setActive(subject.getActive());
					return dto;
				})
				.toList();
	}

}
