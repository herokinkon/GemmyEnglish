package com.project.english.gemmy.model.dto;

import java.util.Date;
import java.util.Set;

import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.StaffInfo;
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
	
	private String campus;
	
	private String address;
	
	private String room;
	
	private String schedule;
	
	private Integer lesson;
	
	private Set<StudentInfo> studentInfos;
	
	private Set<StaffInfo> staffInfos;
	
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
		this.schedule = classesInfo.getSchedule();
		this.studentInfos = classesInfo.getStudentInfos();
		this.staffInfos = classesInfo.getStaffInfos();
		this.lesson = classesInfo.getLesson();
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

	public Set<StudentInfo> getStudentInfos() {
		return studentInfos;
	}

	public void setStudentInfos(Set<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Set<StaffInfo> getStaffInfos() {
		return staffInfos;
	}

	public void setStaffInfos(Set<StaffInfo> staffInfos) {
		this.staffInfos = staffInfos;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
	}

}
