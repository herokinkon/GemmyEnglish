package com.project.english.gemmy.model.dto;

import java.util.Date;

import com.project.english.gemmy.model.jpa.FeePayment;

public class FeePaymentDTO {

	private long id;
	private Date date;
	private Date paymentDate;
	private String kindOfPayment;
	private String reason;
	private String amount;
	private byte month;
	private StudentDTO studentInfo;
	private ClassesInfoDto classes;

	public FeePaymentDTO() {
	}

	public FeePaymentDTO(FeePayment feePayment) {
		this.id = feePayment.getId();
		this.date = feePayment.getDate();
		this.paymentDate = feePayment.getPaymentDate();
		this.kindOfPayment = feePayment.getKindOfPayment();
		this.reason = feePayment.getReason();
		this.month = feePayment.getMonth();
		this.setAmount(feePayment.getAmount());
		this.setStudentInfo(new StudentDTO(feePayment.getStudentInfo()));
		this.setClasses(new ClassesInfoDto(feePayment.getClasses()));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getKindofPayment() {
		return kindOfPayment;
	}

	public void setKindofPayment(String kindofPayment) {
		this.kindOfPayment = kindofPayment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public byte getMonth() {
		return month;
	}

	public void setMonth(byte month) {
		this.month = month;
	}

	public StudentDTO getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentDTO student) {
		this.studentInfo = student;
	}

	public ClassesInfoDto getClasses() {
		return classes;
	}

	public void setClasses(ClassesInfoDto classes) {
		this.classes = classes;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
