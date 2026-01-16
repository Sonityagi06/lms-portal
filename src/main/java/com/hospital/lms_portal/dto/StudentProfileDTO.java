package com.hospital.lms_portal.dto;

import com.hospital.lms_portal.entity.Student;

public class StudentProfileDTO {

	private String rollNumber;
	
	private String name;
	 private String email;
	 private String phone;
	 private String branch;
	 private Integer semester;
	 private Integer enrollYear;
	 
	 public StudentProfileDTO(Student s) {
		 this.rollNumber=s.getRollNumber();
		 this.name=s.getName();
		 this.email=s.getEmail();
		 this.phone=s.getPhone();
		 this.branch=s.getBranch();
		 this.semester=s.getSemester();
		 this.enrollYear=s.getEnrollYear();
	 }

	 
	 
	public StudentProfileDTO() {
		super();
	}



	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Integer getEnrollYear() {
		return enrollYear;
	}

	public void setEnrollYear(Integer enrollYear) {
		this.enrollYear = enrollYear;
	}
	 
	 
}
