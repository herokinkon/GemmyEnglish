package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the exam_result database table.
 * 
 */
@Entity
@Table(name = "exam_result")
public class ExamResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "exam_date")
	private Date examDate;

	private Double listening;

	private Double reading;

	private Double speaking;

	private Double writing;

	private Double overall;

	private Boolean result;

	// bi-directional many-to-one association to Exam
	@ManyToOne
	private Exam exam;

	// bi-directional many-to-one association to StudentInfo
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentInfo studentInfo;

	// bi-directional many-to-one association to StudentInfo
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Classes classes;

	public ExamResult() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Double getListening() {
		return listening;
	}

	public void setListening(Double listening) {
		this.listening = listening;
	}

	public Double getReading() {
		return reading;
	}

	public void setReading(Double reading) {
		this.reading = reading;
	}

	public Double getSpeaking() {
		return speaking;
	}

	public void setSpeaking(Double speaking) {
		this.speaking = speaking;
	}

	public Double getWriting() {
		return writing;
	}

	public void setWriting(Double writing) {
		this.writing = writing;
	}

	public Double getOverall() {
		return overall;
	}

	public void setOverall(Double overall) {
		this.overall = overall;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Exam getExam() {
		return this.exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public StudentInfo getStudentInfo() {
		return this.studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

}