package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the classes database table.
 * 
 */
@Entity
@Table(name="classes")
public class Classes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="class_name")
	private String className;
	
	@Column(name="class_code")
	private String classCode;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String fee;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private Boolean status;
	
	private String basis;
	
	private String address;
	
	private String room;

	//bi-directional many-to-one association to Course
	@ManyToOne
	private Course course;

	//bi-directional many-to-one association to FeePayment
	@JsonIgnore
	@OneToMany(mappedBy="classes")
	private List<FeePayment> feePayments;

	//bi-directional many-to-many association to StaffInfo
	@ManyToMany
	@JoinTable(name = "classes_has_staff_info", joinColumns = { @JoinColumn(name = "classes_id") }, inverseJoinColumns = {
			@JoinColumn(name = "staff_info_id") })
	private Set<StaffInfo> staffInfos;

	//bi-directional many-to-many association to StudentInfo
	@ManyToMany
	@JoinTable(name = "student_classes", joinColumns = { @JoinColumn(name = "classes_id") }, inverseJoinColumns = {
			@JoinColumn(name = "student_info_id") })
	private Set<StudentInfo> studentInfos;
	
	// bi-directional many-to-one association to ExamResult
	@JsonIgnore
	@OneToMany(mappedBy = "classes")
	private List<ExamResult> examResults;

	public Classes() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFee() {
		return this.fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<FeePayment> getFeePayments() {
		return this.feePayments;
	}

	public void setFeePayments(List<FeePayment> feePayments) {
		this.feePayments = feePayments;
	}

	public FeePayment addFeePayment(FeePayment feePayment) {
		getFeePayments().add(feePayment);
		feePayment.setClasses(this);

		return feePayment;
	}

	public FeePayment removeFeePayment(FeePayment feePayment) {
		getFeePayments().remove(feePayment);
		feePayment.setClasses(null);

		return feePayment;
	}

	public Set<StaffInfo> getStaffInfos() {
		return this.staffInfos;
	}

	public void setStaffInfos(Set<StaffInfo> staffInfos) {
		this.staffInfos = staffInfos;
	}

	public Set<StudentInfo> getStudentInfos() {
		return this.studentInfos;
	}

	public void setStudentInfos(Set<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	public List<ExamResult> getExamResults() {
		return this.examResults;
	}

	public void setExamResults(List<ExamResult> examResults) {
		this.examResults = examResults;
	}

	public ExamResult addExamResult(ExamResult examResult) {
		getExamResults().add(examResult);
		examResult.setClasses(this);

		return examResult;
	}

	public ExamResult removeExamResult(ExamResult examResult) {
		getExamResults().remove(examResult);
		examResult.setClasses(null);

		return examResult;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}