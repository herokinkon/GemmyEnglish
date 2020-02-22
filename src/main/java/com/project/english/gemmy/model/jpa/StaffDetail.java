package com.project.english.gemmy.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the staff_detail database table.
 * 
 */
@Entity
@Table(name="staff_detail")
public class StaffDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String salary;

	@Column(name="staff_type")
	private String staffType;

	@Column(name="work_of_staff")
	private String workOfStaff;

	//bi-directional many-to-one association to StaffInfo
	@ManyToOne
	@JoinColumn(name="staff_id")
	private StaffInfo staffInfo;

	public StaffDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getStaffType() {
		return this.staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getWorkOfStaff() {
		return this.workOfStaff;
	}

	public void setWorkOfStaff(String workOfStaff) {
		this.workOfStaff = workOfStaff;
	}

	public StaffInfo getStaffInfo() {
		return this.staffInfo;
	}

	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}

}