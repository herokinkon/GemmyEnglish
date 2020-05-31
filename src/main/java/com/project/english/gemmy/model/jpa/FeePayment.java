package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the fee_payment database table.
 * 
 */
@Entity
@Table(name = "fee_payment")
public class FeePayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;
	
	private String discount;

	@Column(name = "kind_of_payment")
	private String kindOfPayment;

	private byte month;

	private String reason;

	@Column(name = "amount")
	private String amount;

	// bi-directional many-to-one association to Class
	@ManyToOne
	@JoinColumn(name = "classes_id")
	private Classes classes;

	// bi-directional many-to-one association to StudentInfo
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentInfo studentInfo;

	public FeePayment() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getKindOfPayment() {
		return this.kindOfPayment;
	}

	public void setKindOfPayment(String kindOfPayment) {
		this.kindOfPayment = kindOfPayment;
	}

	public byte getMonth() {
		return this.month;
	}

	public void setMonth(byte month) {
		this.month = month;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}