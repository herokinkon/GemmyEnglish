package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name="user_account")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="join_date")
	private Date joinDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login")
	private Date lastLogin;

	private String password;

	private Boolean status;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to StaffInfo
	@OneToMany(mappedBy="userAccount")
	private List<StaffInfo> staffInfos;

	//bi-directional many-to-one association to StudentInfo
	@OneToMany(mappedBy="userAccount")
	private List<StudentInfo> studentInfos;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="user_account_has_role"
		, joinColumns={
			@JoinColumn(name="user_account_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public UserAccount() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
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

	public List<StaffInfo> getStaffInfos() {
		return this.staffInfos;
	}

	public void setStaffInfos(List<StaffInfo> staffInfos) {
		this.staffInfos = staffInfos;
	}

	public StaffInfo addStaffInfo(StaffInfo staffInfo) {
		getStaffInfos().add(staffInfo);
		staffInfo.setUserAccount(this);

		return staffInfo;
	}

	public StaffInfo removeStaffInfo(StaffInfo staffInfo) {
		getStaffInfos().remove(staffInfo);
		staffInfo.setUserAccount(null);

		return staffInfo;
	}

	public List<StudentInfo> getStudentInfos() {
		return this.studentInfos;
	}

	public void setStudentInfos(List<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

	public StudentInfo addStudentInfo(StudentInfo studentInfo) {
		getStudentInfos().add(studentInfo);
		studentInfo.setUserAccount(this);

		return studentInfo;
	}

	public StudentInfo removeStudentInfo(StudentInfo studentInfo) {
		getStudentInfos().remove(studentInfo);
		studentInfo.setUserAccount(null);

		return studentInfo;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}