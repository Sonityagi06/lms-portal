package com.hospital.lms_portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.CourseFacultyAssignDTO;
import com.hospital.lms_portal.entity.Course;
import com.hospital.lms_portal.entity.CourseFaculty;
import com.hospital.lms_portal.entity.Faculty;
import com.hospital.lms_portal.repository.CourseFacultyRepository;
import com.hospital.lms_portal.repository.CourseRepository;
import com.hospital.lms_portal.repository.FacultyRepository;

@Service
public class CourseFacultyServiceImpl implements CourseFacultyService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private FacultyRepository facultyRepo;
	
	@Autowired
	private CourseFacultyRepository courseFacultyRepo;
	
	@Autowired
	private FacultyService facultyService;
	
	@Override
	public void assignFacultyToCourse(CourseFacultyAssignDTO dto) {
	
		Course course = courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new RuntimeException("Course not found"));
		
		Faculty faculty = facultyRepo.findById(dto.getFacultyId())
				.orElseThrow(() -> new RuntimeException("Faculty not found"));
		
		if(courseFacultyRepo.existsByCourseAndFaculty(course, faculty)) {
			throw new RuntimeException("Faculty already assigned to this course");
		}
		
		CourseFaculty cf = new CourseFaculty();
		cf.setCourse(course);
		cf.setFaculty(faculty);
		
		courseFacultyRepo.save(cf);
	}

	
	@Override
	public List<Course> getFacultyCourses() {
		
		Faculty faculty = facultyService.getLoggedInFaculty();
		
		return courseFacultyRepo.findByFaculty(faculty)
				.stream()
				.map(CourseFaculty::getCourse)
				.toList();
	}

}
