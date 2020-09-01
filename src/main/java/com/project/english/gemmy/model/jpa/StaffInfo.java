package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the staff_info database table.
 * 
 */
@Entity
@Table(name="staff_info")
public class StaffInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="contact_number")
	private String contactNumber;

	private String email;

	private String facebook;

	@Column(name="full_name")
	private String fullName;
	
	private String salary;

	@Column(name="staff_type")
	private String staffType;

	@Column(name="work_of_staff")
	private String workOfStaff;
	
	@Column(name="bank_account")
	private String bankAccount;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="bank_branch")
	private String bankBranch;
	
	@Column(name="ielts_score")
	private Double ieltsScore;
	
	@Column(name="others_certificate")
	private String othersCertificate;

	// bi-directional many-to-many association to Class
	@JsonIgnore
	@ManyToMany(mappedBy="staffInfos")
	private Set<Classes> classes;

	//bi-directional many-to-one association to UserAccount
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_account_id")
	private UserAccount userAccount;
	
	// bi-directional many-to-one association to OthersOutcome
	@JsonIgnore
	@OneToMany(mappedBy = "staffInfo")
	private List<OthersOutcome> othersOutcome;

	public StaffInfo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Set<Classes> getClasses() {
		return this.classes;
	}

	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getStaffType() {
		return this.staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getWorkOfStaff() {
		return this.workOfStaff;
	}

	public void setWorkOfStaff(String workOfStaff) {
		this.workOfStaff = workOfStaff;
	}

	public List<OthersOutcome> getOthersOutcome() {
		return othersOutcome;
	}

	public void setOthersOutcome(List<OthersOutcome> othersOutcome) {
		this.othersOutcome = othersOutcome;
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

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

}