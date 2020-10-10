package com.project.english.gemmy.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.project.english.gemmy.model.jpa.enumerate.Role;

/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonInclude(Include.NON_NULL)
	private String password;

	private Boolean status;

	@Column(name = "user_name")
	private String userName;

	@OneToOne()
	@JoinColumn(name = "staff_id")
	private StaffInfo staff;

	@Enumerated(EnumType.STRING)
	private Role roles;

	public UserAccount() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public StaffInfo getStaff() {
		return staff;
	}

	public void setStaff(StaffInfo staff) {
		this.staff = staff;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

}