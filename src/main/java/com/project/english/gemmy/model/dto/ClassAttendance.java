package com.project.english.gemmy.model.dto;

public class ClassAttendance {

	private ClassesInfoDto classInfo;
	
	private StudentInfoDto[] studentInfo;

	public ClassesInfoDto getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassesInfoDto classInfo) {
		this.classInfo = classInfo;
	}

	public StudentInfoDto[] getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfoDto[] studentInfo) {
		this.studentInfo = studentInfo;
	}

}
