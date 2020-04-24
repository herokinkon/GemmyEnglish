package com.project.english.gemmy.model.dto;

import java.util.Map;

public class Attendance {

	private Long classId;
	
	private Map<String, Integer> attendance;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Map<String, Integer> getAttendance() {
		return attendance;
	}

	public void setAttendance(Map<String, Integer> attendance) {
		this.attendance = attendance;
	}
	
}
