package com.project.english.gemmy.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.project.english.gemmy.model.jpa.Classes;
import com.project.english.gemmy.model.jpa.StaffInfo;

@JsonInclude(Include.NON_NULL)
public class StaffDTO {

	private Long id;
	private Date birthday;
	private String contactNumber;
	private String email;
	private String facebook;
	private String fullName;
	private String salary;
	private String staffType;
	private String workOfStaff;
	private List<ClassesInfoDto> classes;
	
	private String bankAccount;
	
	private String bankName;
	
	private String bankBranch;
	
	private Double ieltsScore;
	
	private String othersCertificate;

	public StaffDTO() {

	}

	public StaffDTO(long id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public StaffDTO(StaffInfo staffInfo) {
		this.id = staffInfo.getId();
		this.birthday = staffInfo.getBirthday();
		this.contactNumber = staffInfo.getContactNumber();
		this.email = staffInfo.getEmail();
		this.facebook = staffInfo.getFacebook();
		this.fullName = staffInfo.getFullName();
		this.setSalary(staffInfo.getSalary());
		this.setStaffType(staffInfo.getStaffType());
		this.setWorkOfStaff(staffInfo.getWorkOfStaff());
		Set<Classes> clazz = staffInfo.getClasses();
		if (clazz != null) {
			this.setClasses(clazz.stream().map(ClassesInfoDto::new).collect(Collectors.toList()));
		} else {
			this.setClasses(new ArrayList<>());
		}
		this.bankAccount = staffInfo.getBankAccount();
		this.bankBranch = staffInfo.getBankBranch();
		this.bankName = staffInfo.getBankName();
		this.ieltsScore = staffInfo.getIeltsScore();
		this.othersCertificate = staffInfo.getOthersCertificate();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getWorkOfStaff() {
		return workOfStaff;
	}

	public void setWorkOfStaff(String workOfStaff) {
		this.workOfStaff = workOfStaff;
	}

	public List<ClassesInfoDto> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassesInfoDto> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return String.format("Id: %s, Name: %s, birthday: %s", this.id, this.fullName, this.birthday);
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public Double getIeltsScore() {
		return ieltsScore;
	}

	public void setIeltsScore(Double ieltsScore) {
		this.ieltsScore = ieltsScore;
	}

	public String getOthersCertificate() {
		return othersCertificate;
	}

	public void setOthersCertificate(String othersCertificate) {
		this.othersCertificate = othersCertificate;
	}
}
