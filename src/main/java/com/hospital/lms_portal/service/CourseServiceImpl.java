package com.hospital.lms_portal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.CourseCreateDTO;
import com.hospital.lms_portal.dto.CourseResponseDTO;
import com.hospital.lms_portal.entity.Course;
import com.hospital.lms_portal.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public CourseResponseDTO createCourse(CourseCreateDTO dto) {
		
		if(courseRepository.existsByCourseCode(dto.getCourseCode())) {
			throw new RuntimeException("Course code already exists");
		}
		Course course = new Course();
		course.setCourseCode(dto.getCourseCode());
		course.setCourseName(dto.getCourseName());
		course.setDescription(dto.getDescription());
		course.setDuration(dto.getDuration());
		course.setActive(true);
		course.setCreatedAt(LocalDateTime.now());
		
		Course saved = courseRepository.save(course);
		
		CourseResponseDTO response = new CourseResponseDTO();
		response.setId(saved.getId());
		response.setCourseCode(saved.getCourseCode());
		response.setCourseName(saved.getCourseName());
		response.setActive(saved.getActive());
		
		return response;
	}

	@Override
	public List<CourseResponseDTO> getAllCourses() {
		
		
		return courseRepository.findAll()
				.stream()
				.map(course ->{
					CourseResponseDTO dto = new CourseResponseDTO();
					dto.setId(course.getId());
					dto.setCourseCode(course.getCourseCode());
					dto.setCourseName(course.getCourseName());
					dto.setActive(course.getActive());
					return dto;
				})
				.toList();
	}

}
