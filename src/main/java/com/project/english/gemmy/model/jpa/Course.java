package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Class
	@OneToMany(mappedBy="course")
	private List<Classes> classes;

	public Course() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Classes> getClasses() {
		return this.classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public Classes addClasses(Classes classes) {
		getClasses().add(classes);
		classes.setCourse(this);

		return classes;
	}

	public Classes removeClass(Classes classes) {
		getClasses().remove(classes);
		classes.setCourse(null);

		return classes;
	}

}