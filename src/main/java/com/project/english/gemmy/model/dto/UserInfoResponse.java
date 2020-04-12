package com.project.english.gemmy.model.dto;

import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.jpa.StudentInfo;

public class UserInfoResponse {
	
	private Long userId;

	private String fullName;
	
	private String birthdate;
	
	private String email;
	
	private String facebook;
	
	private String contactNumber;
	
	private String parentEmail;
	
	private String parentContactNumber;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	} 

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getParentContactNumber() {
		return parentContactNumber;
	}

	public void setParentContactNumber(String parentContactNumber) {
		this.parentContactNumber = parentContactNumber;
	}

	public void convertEntityToStaffObject(StaffInfo staffInfo) {
		// TODO Auto-generated method stub
		
	}

	public void convertEntityToStudentObject(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		
	}

}
