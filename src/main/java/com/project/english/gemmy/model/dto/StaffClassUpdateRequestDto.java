package com.project.english.gemmy.model.dto;

import com.project.english.gemmy.model.jpa.StaffInfo;

public class StaffClassUpdateRequestDto {
	
	private StaffInfo[] staffSource;
	
	private StaffInfo[] staffTarget;
	
	private Long classSourceId;
	
	private Long classTargetId;

	public StaffInfo[] getStaffSource() {
		return staffSource;
	}

	public void setStaffSource(StaffInfo[] staffSource) {
		this.staffSource = staffSource;
	}

	public StaffInfo[] getStaffTarget() {
		return staffTarget;
	}

	public void setStaffTarget(StaffInfo[] staffTarget) {
		this.staffTarget = staffTarget;
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
