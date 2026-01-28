package com.hospital.lms_portal.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.lms_portal.dto.EnrollmentRequestDTO;
import com.hospital.lms_portal.dto.EnrollmentResponseDTO;
import com.hospital.lms_portal.entity.Course;
import com.hospital.lms_portal.entity.Enrollment;
import com.hospital.lms_portal.entity.Student;
import com.hospital.lms_portal.repository.CourseRepository;
import com.hospital.lms_portal.repository.EnrollmentRepository;
import com.hospital.lms_portal.repository.StudentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

	@Autowired
	private EnrollmentRepository enrollmentRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	
	@Override
	public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto) {
		
		Student student = studentRepo.findById(dto.getStudentId())
				.orElseThrow(() -> new RuntimeException("Student not found"));
		
		Course course = courseRepo.findById(dto.getCourseId())
				.orElseThrow(() -> new RuntimeException("Course not found"));
		
		enrollmentRepo.findByStudentIdAndCourseId(student.getId(), course.getId()
				).ifPresent(e -> {
					throw new RuntimeException("Student already enrolled");
				});
		
		Enrollment enrollment = new Enrollment();
		enrollment.setStudent(student);
		enrollment.setCourse(course);
		enrollment.setEnrolledDate(LocalDate.now());
		enrollment.setStatus("ENROLLED");
	
		Enrollment saved = enrollmentRepo.save(enrollment);
		
		EnrollmentResponseDTO response = new EnrollmentResponseDTO();
		response.setEnrollmentId(saved.getId());
		response.setStudentId(student.getId());
		response.setStudentName(student.getName());
		response.setCourseId(course.getId());
		response.setCourseName(course.getCourseName());
		response.setStatus(saved.getStatus());
		
		return response;
	}

}
