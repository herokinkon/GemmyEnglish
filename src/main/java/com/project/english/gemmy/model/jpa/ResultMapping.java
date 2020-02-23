package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the result_mapping database table.
 * 
 */
@Entity
@Table(name="result_mapping")
public class ResultMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="band_level")
	private String bandLevel;

	private Double listening;

	private Double overall;

	private Double reading;

	private Double speaking;

	private Double writing;

	public ResultMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBandLevel() {
		return this.bandLevel;
	}

	public void setBandLevel(String bandLevel) {
		this.bandLevel = bandLevel;
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

}