package com.project.english.gemmy.model.response;

import java.util.Date;

import com.project.english.gemmy.model.jpa.Classes;

public class ClassesInfoResponse {

	private Long id;

	private String className;
	
	private String classCode;

	private String description;

	private Date endDate;

	private String fee;

	private Date startDate;

	private Boolean status;
	
	public ClassesInfoResponse() {
		
	}
	
	public ClassesInfoResponse(Classes classesInfo) {
		this.id = classesInfo.getId();
		this.className = classesInfo.getClassName();
		this.classCode = classesInfo.getClassCode();
		this.description = classesInfo.getDescription();
		this.endDate = classesInfo.getEndDate();
		this.fee = classesInfo.getFee();
		this.startDate = classesInfo.getStartDate();
		this.status = classesInfo.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
