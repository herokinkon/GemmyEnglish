package com.project.english.gemmy.model.dto;

import com.project.english.gemmy.model.jpa.StudentInfo;

public class StudentClassUpdateRequestDto {

	private StudentInfo[] studentSource;
	
	private StudentInfo[] studentTarget;
	
	private Long classSourceId;
	
	private Long classTargetId;

	public StudentInfo[] getStudentSource() {
		return studentSource;
	}

	public void setStudentSource(StudentInfo[] studentSource) {
		this.studentSource = studentSource;
	}

	public StudentInfo[] getStudentTarget() {
		return studentTarget;
	}

	public void setStudentTarget(StudentInfo[] studentTarget) {
		this.studentTarget = studentTarget;
	}

	public Long getClassSourceId() {
		return classSourceId;
	}

	public void setClassSourceId(Long classSourceId) {
		this.classSourceId = classSourceId;
	}

	public Long getClassTargetId() {
		return classTargetId;
	}

	public void setClassTargetId(Long classTargetId) {
		this.classTargetId = classTargetId;
	}
}
