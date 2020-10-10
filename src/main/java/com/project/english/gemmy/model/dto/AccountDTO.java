package com.project.english.gemmy.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.jpa.enumerate.Role;

@JsonInclude(Include.NON_NULL)
public class AccountDTO {

	private Long id;
	private String userName;
	private Boolean status;
	private StaffDTO staff;
	private Role roles;
	private String password;

	public AccountDTO() {

	}

	public AccountDTO(UserAccount account) {
		// Ignore password.
		this.id = account.getId();
		this.userName = account.getUserName();
		this.status = account.getStatus();
		this.roles = account.getRoles();
		this.staff = new StaffDTO();
		StaffInfo nStaff = account.getStaff();
		if (nStaff != null) {
			this.staff.setId(account.getStaff().getId());
			this.staff.setFullName(account.getStaff().getFullName());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public StaffDTO getStaff() {
		return staff;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
