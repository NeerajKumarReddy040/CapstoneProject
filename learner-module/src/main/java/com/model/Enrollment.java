package com.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private int enrollmentId;

	
	@Column(name = "academy_id", nullable = false)
	private int academyId;

	@Column(name = "enrollment_date")
	private Date enrollmentDate;
	
	@Column(name= "learner_id")
	private Integer learnerId;

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Integer getLearnerId() {
		return learnerId;
	}

	public void setLearnerId(Integer learnerId) {
		this.learnerId = learnerId;
	}


	public Enrollment(int academyId, Date enrollmentDate, Integer learnerId) {
		super();
		this.academyId = academyId;
		this.enrollmentDate = enrollmentDate;
		this.learnerId = learnerId;
	}

	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", academyId=" + academyId + ", enrollmentDate="
				+ enrollmentDate + ", learnerId=" + learnerId + "]";
	}

	

}