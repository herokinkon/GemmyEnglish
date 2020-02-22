package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the exam_result database table.
 * 
 */
@Entity
@Table(name="exam_result")
@NamedQuery(name="ExamResult.findAll", query="SELECT e FROM ExamResult e")
public class ExamResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="exam_date")
	private Date examDate;

	private Double listening;

	private Double overall;

	private Double reading;

	private String result;

	private Double speaking;

	private Double writing;

	//bi-directional many-to-one association to Exam
	@ManyToOne
	private Exam exam;

	//bi-directional many-to-one association to StudentInfo
	@ManyToOne
	@JoinColumn(name="student_id")
	private StudentInfo studentInfo;

	public ExamResult() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExamDate() {
		return this.examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Double getListening() {
		return this.listening;
	}

	public void setListening(Double listening) {
		this.listening = listening;
	}

	public Double getOverall() {
		return this.overall;
	}

	public void setOverall(Double overall) {
		this.overall = overall;
	}

	public Double getReading() {
		return this.reading;
	}

	public void setReading(Double reading) {
		this.reading = reading;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Double getSpeaking() {
		return this.speaking;
	}

	public void setSpeaking(Double speaking) {
		this.speaking = speaking;
	}

	public Double getWriting() {
		return this.writing;
	}

	public void setWriting(Double writing) {
		this.writing = writing;
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

}