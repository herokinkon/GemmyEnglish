package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the exam database table.
 * 
 */
@Entity
public class Exam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	@Column(name="exam_type")
	private String examType;

	private String name;

	//bi-directional many-to-one association to ExamResult
	@JsonIgnore
	@OneToMany(mappedBy="exam")
	private List<ExamResult> examResults;

	public Exam() {
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

	public String getExamType() {
		return this.examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExamResult> getExamResults() {
		return this.examResults;
	}

	public void setExamResults(List<ExamResult> examResults) {
		this.examResults = examResults;
	}

	public ExamResult addExamResult(ExamResult examResult) {
		getExamResults().add(examResult);
		examResult.setExam(this);

		return examResult;
	}

	public ExamResult removeExamResult(ExamResult examResult) {
		getExamResults().remove(examResult);
		examResult.setExam(null);

		return examResult;
	}

}