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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the staff_info database table.
 * 
 */
@Entity
public class Timeline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	// For normal event
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;

	@Column(name = "day_of_week")
	private String daysOfWeek;

	// For recurrence event
	private String startTime;
	private String endTime;
	@Temporal(TemporalType.DATE)
	private Date startRecur;
	@Temporal(TemporalType.DATE)
	private Date endRecur;

	private String description;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private StaffInfo staff;

	public Timeline() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StaffInfo getStaff() {
		return staff;
	}

	public void setStaff(StaffInfo staff) {
		this.staff = staff;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getStartRecur() {
		return startRecur;
	}

	public void setStartRecur(Date startRecur) {
		this.startRecur = startRecur;
	}

	public Date getEndRecur() {
		return endRecur;
	}

	public void setEndRecur(Date endRecur) {
		this.endRecur = endRecur;
	}

}