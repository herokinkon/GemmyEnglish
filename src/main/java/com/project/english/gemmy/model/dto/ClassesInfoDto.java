package com.project.english.gemmy.model.dto;

import java.util.Date;
import java.util.Set;

import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.StudentInfo;

public class ClassesInfoDto {

	private Long id;

	private String className;
	
	private String classCode;

	private String description;

	private Date endDate;

	private String fee;

	private Date startDate;

	private Boolean status;
	
	private Long courseId;
	
	private String courseName;
	
	private String courseDescription;
	
	private Set<StudentInfo> studentInfos;
	
	public ClassesInfoDto() {
		
	}
	
	public ClassesInfoDto(Classes classesInfo) {
		this.id = classesInfo.getId();
		this.className = classesInfo.getClassName();
		this.classCode = classesInfo.getClassCode();
		this.description = classesInfo.getDescription();
		this.endDate = classesInfo.getEndDate();
		this.fee = classesInfo.getFee();
		this.startDate = classesInfo.getStartDate();
		this.status = classesInfo.getStatus();
		if (classesInfo.getCourse() != null) {
			this.courseId = classesInfo.getCourse().getId();
			this.courseName = classesInfo.getCourse().getName();
			this.courseDescription = classesInfo.getCourse().getDescription();
		}
		this.studentInfos = classesInfo.getStudentInfos();
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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Set<StudentInfo> getStudentInfos() {
		return studentInfos;
	}

	public void setStudentInfos(Set<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

}
