package com.project.english.gemmy.model.dto;

import java.util.Date;

import com.project.english.gemmy.model.jpa.OthersOutcome;

public class OthersOutcomeDto {
	private Long id;

	private Date usedDate;

	private String purpose;

	private String cost;
	
	private Boolean status;
	
	private String fullName;
	
	private Date birthday;
	
	private Long staffId;
	
	public OthersOutcomeDto() {
		
	}
	
	public OthersOutcomeDto(OthersOutcome otherOutCome) {
		this.setId(otherOutCome.getId());
		this.setCost(otherOutCome.getCost());
		this.setPurpose(otherOutCome.getPurpose());
		this.setStatus(otherOutCome.getStatus());
		this.setUsedDate(otherOutCome.getUsedDate());
		this.setFullName(otherOutCome.getStaffInfo().getFullName());
		this.setBirthday(otherOutCome.getStaffInfo().getBirthday());
		this.setStaffId(otherOutCome.getStaffInfo().getId());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
}
