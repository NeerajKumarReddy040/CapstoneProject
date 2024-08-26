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

	@ManyToOne
	@JoinColumn(name = "learner_id", nullable = false)
	private Learner learner;

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

	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	public Enrollment(int academyId, Date enrollmentDate, Learner learner) {
		super();
		this.academyId = academyId;
		this.enrollmentDate = enrollmentDate;
		this.learner = learner;
	}

	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", academyId=" + academyId + ", enrollmentDate="
				+ enrollmentDate + ", learner=" + learner + "]";
	}

}