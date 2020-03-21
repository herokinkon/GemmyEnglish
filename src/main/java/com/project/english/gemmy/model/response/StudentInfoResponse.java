package com.project.english.gemmy.model.response;

import java.util.Date;

import com.project.english.gemmy.model.jpa.StudentInfo;

public class StudentInfoResponse {
	
	private Long id;

	private Date birthday;

	private String contactNumber;

	private String email;

	private String facebook;

	private String fullName;

	private String parentContactNumber;

	private String parentEmail;
	
	private String attendance;
	
	private String classCode;
	
	public StudentInfoResponse() {
		
	}
	
	public StudentInfoResponse(StudentInfo studentInfo) {
		this.id = studentInfo.getId();
		this.birthday = studentInfo.getBirthday();
		this.attendance = studentInfo.getAttendance();
		this.contactNumber = studentInfo.getContactNumber();
		this.email = studentInfo.getEmail();
		this.facebook = studentInfo.getFacebook();
		this.fullName = studentInfo.getFullName();
		this.parentContactNumber = studentInfo.getParentContactNumber();
		this.parentEmail = studentInfo.getParentEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getParentContactNumber() {
		return parentContactNumber;
	}

	public void setParentContactNumber(String parentContactNumber) {
		this.parentContactNumber = parentContactNumber;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
}
