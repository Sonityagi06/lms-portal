package com.hospital.lms_portal.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.AssignSubjectDTO;
import com.hospital.lms_portal.dto.FacultySubjectResponseDTO;
import com.hospital.lms_portal.entity.Faculty;
import com.hospital.lms_portal.entity.FacultySubject;
import com.hospital.lms_portal.entity.Subject;
import com.hospital.lms_portal.repository.FacultyRepository;
import com.hospital.lms_portal.repository.FacultySubjectRepository;
import com.hospital.lms_portal.repository.SubjectRepository;

@Service
public class FacultySubjectServiceImpl implements FacultySubjectService{

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private FacultySubjectRepository facultySubjectRepository;
	@Override
	public FacultySubjectResponseDTO assignSubjectToFaculty(AssignSubjectDTO dto) {
		
		if(facultySubjectRepository.existsByFacultyIdAndSubjectId(dto.getFacultyId(), dto.getSubjectId())) {
			throw new RuntimeException("Subject already assigned to faculty");
		}
		Faculty faculty = facultyRepository.findById(dto.getFacultyId())
				.orElseThrow(() -> new RuntimeException("Faculty not found"));
		
		Subject subject = subjectRepository.findById(dto.getSubjectId())
				.orElseThrow(() -> new RuntimeException("Subject not found"));
		
		FacultySubject fs = new FacultySubject();
		fs.setFaculty(faculty);
		fs.setSubject(subject);
		fs.setAssignedAt(LocalDateTime.now());
		fs.setActive(true);
		
		FacultySubject saved = facultySubjectRepository.save(fs);
		
		FacultySubjectResponseDTO response = new FacultySubjectResponseDTO();
		response.setId(saved.getId());
		response.setFacultyName(faculty.getName());
		response.setSubjectName(subject.getSubjectName());
		response.setActive(saved.getActive());
		
		return response;
	}

}
