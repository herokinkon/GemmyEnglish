package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the classes database table.
 * 
 */
@Entity
@Table(name="classes")
@NamedQuery(name="Classes.findAll", query="SELECT c FROM Classes c")
public class Classes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	private String fee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	private Boolean status;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="classes")
	private List<Attendance> attendances;

	//bi-directional many-to-one association to Course
	@ManyToOne
	private Course course;

	//bi-directional many-to-one association to FeePayment
	@OneToMany(mappedBy="classes")
	private List<FeePayment> feePayments;

	//bi-directional many-to-many association to StaffInfo
	@ManyToMany(mappedBy="classes")
	private List<StaffInfo> staffInfos;

	//bi-directional many-to-many association to StudentInfo
	@ManyToMany(mappedBy="classes")
	private List<StudentInfo> studentInfos;

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

	public List<Attendance> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Attendance addAttendance(Attendance attendance) {
		getAttendances().add(attendance);
		attendance.setClasses(this);

		return attendance;
	}

	public Attendance removeAttendance(Attendance attendance) {
		getAttendances().remove(attendance);
		attendance.setClasses(null);

		return attendance;
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

	public List<StaffInfo> getStaffInfos() {
		return this.staffInfos;
	}

	public void setStaffInfos(List<StaffInfo> staffInfos) {
		this.staffInfos = staffInfos;
	}

	public List<StudentInfo> getStudentInfos() {
		return this.studentInfos;
	}

	public void setStudentInfos(List<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}

}