package com.project.english.gemmy.model.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.jpa.Timeline;

@JsonInclude(Include.NON_NULL)
public class TimelineDTO {

	private Long id;
	private String title;
	private Date start;
	private Date end;
	private List<Integer> daysOfWeek = new ArrayList<>();
	private String startTime;
	private String endTime;
	private Date startRecur;
	private Date endRecur;
	private String description;
	private StaffDTO staff;

	public TimelineDTO() {

	}

	public TimelineDTO(Timeline timeline) {
		this.id = timeline.getId();
		this.setTitle(timeline.getTitle());
		this.start = timeline.getStart();
		this.end = timeline.getEnd();
		this.setDOW(timeline.getDaysOfWeek());
		this.startTime = timeline.getStartTime();
		this.endTime = timeline.getEndTime();
		this.startRecur = timeline.getStartRecur();
		this.endRecur = timeline.getEndRecur();
		this.setDescription(timeline.getDescription());
		StaffInfo staffInfo = timeline.getStaff();
		this.setStaff(new StaffDTO(staffInfo.getId(), staffInfo.getFullName()));
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StaffDTO getStaff() {
		return staff;
	}

	public void setStaff(StaffDTO staff) {
		this.staff = staff;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getDaysOfWeek() {
		return this.daysOfWeek.isEmpty() ? null : this.daysOfWeek.toString();
	}

	public void setDaysOfWeek(Integer[] daysOfWeek) {
		if (daysOfWeek != null) {
			this.daysOfWeek = Arrays.asList(daysOfWeek);
		}
	}

	@SuppressWarnings("unchecked")
	public void setDOW(String daysOfWeek) {
		try {
			if (daysOfWeek != null) {
				this.daysOfWeek = new ObjectMapper().readValue(daysOfWeek, List.class);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
