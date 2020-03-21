package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student_info database table.
 * 
 */
@Entity
@Table(name="student_info")
public class StudentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="contact_number")
	private String contactNumber;

	private String email;

	private String facebook;

	@Column(name="full_name")
	private String fullName;

	@Column(name="parent_contact_number")
	private String parentContactNumber;

	@Column(name="parent_email")
	private String parentEmail;

	//bi-directional many-to-one association to ExamResult
	@OneToMany(mappedBy="studentInfo")
	private List<ExamResult> examResults;

	//bi-directional many-to-one association to FeePayment
	@OneToMany(mappedBy="studentInfo")
	private List<FeePayment> feePayments;

	//bi-directional many-to-many association to Class
	@ManyToMany
	@JoinTable(
		name="student_classes"
		, joinColumns={
			@JoinColumn(name="student_info_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="classes_id")
			}
		)
	private List<Classes> classes;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name="user_account_id")
	private UserAccount userAccount;
	
	private String attendance;

	public StudentInfo() {
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

	public String getParentContactNumber() {
		return this.parentContactNumber;
	}

	public void setParentContactNumber(String parentContactNumber) {
		this.parentContactNumber = parentContactNumber;
	}

	public String getParentEmail() {
		return this.parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public List<ExamResult> getExamResults() {
		return this.examResults;
	}

	public void setExamResults(List<ExamResult> examResults) {
		this.examResults = examResults;
	}

	public ExamResult addExamResult(ExamResult examResult) {
		getExamResults().add(examResult);
		examResult.setStudentInfo(this);

		return examResult;
	}

	public ExamResult removeExamResult(ExamResult examResult) {
		getExamResults().remove(examResult);
		examResult.setStudentInfo(null);

		return examResult;
	}

	public List<FeePayment> getFeePayments() {
		return this.feePayments;
	}

	public void setFeePayments(List<FeePayment> feePayments) {
		this.feePayments = feePayments;
	}

	public FeePayment addFeePayment(FeePayment feePayment) {
		getFeePayments().add(feePayment);
		feePayment.setStudentInfo(this);

		return feePayment;
	}

	public FeePayment removeFeePayment(FeePayment feePayment) {
		getFeePayments().remove(feePayment);
		feePayment.setStudentInfo(null);

		return feePayment;
	}

	public List<Classes> getClasses() {
		return this.classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

}