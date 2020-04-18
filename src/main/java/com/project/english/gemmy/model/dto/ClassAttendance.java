package com.project.english.gemmy.model.dto;

public class ClassAttendance {

	private ClassesInfoDto classInfo;
	
	private StudentDTO[] studentInfo;

	public ClassesInfoDto getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassesInfoDto classInfo) {
		this.classInfo = classInfo;
	}

	public StudentDTO[] getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentDTO[] studentInfo) {
		this.studentInfo = studentInfo;
	}

}
