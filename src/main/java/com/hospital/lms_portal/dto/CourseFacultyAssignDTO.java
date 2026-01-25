package com.hospital.lms_portal.dto;

public class CourseFacultyAssignDTO {

	private Long courseId;
	
	private Long facultyId;
	
	

	public CourseFacultyAssignDTO() {
		super();
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
}
