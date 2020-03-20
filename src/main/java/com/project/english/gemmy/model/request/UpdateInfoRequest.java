package com.project.english.gemmy.model.request;

import java.util.Date;

public class UpdateInfoRequest {
	
	private Long userId;
	
	private String fullName;
	
	private Date birthday;
	
	private String email;
	
	private String facebook;
	
	private String contactNumber;
	
	private String parentEmail;
	
	private String parentContactNumber;
	
	private String typeOfUSer;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getTypeOfUSer() {
		return typeOfUSer;
	}

	public void setTypeOfUSer(String typeOfUSer) {
		this.typeOfUSer = typeOfUSer;
	}

}
