package com.project.english.gemmy.model.dto;

import java.util.Date;

public class UpdateInfoRequest {
	
	// staffId if user is staff
	// studentIf if user is student
	private Long id;
	
	private String fullName;
	
	private Date birthday;
	
	private String email;
	
	private String facebook;
	
	private String contactNumber;
	
	private String parentEmail;
	
	private String parentContactNumber;
	
	private String typeOfUSer;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
