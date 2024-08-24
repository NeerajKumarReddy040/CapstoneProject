package com.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "learner")
public class Learner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "learner_id")
	private int learnerId;

	@Column(name = "fullname", nullable = false)
	@NotEmpty(message ="FullName Required")
	private String fullname;

	@Column(name = "gender", length = 20)
	@NotEmpty(message ="Gender Required")
	private String gender;

	@Column(name = "birthdate")
	@NotNull(message ="Birthdate is Required")
	private Date birthdate;

	@Column(name = "address", length = 255)
	@NotEmpty(message ="Address Required")
	private String address;

	@Column(name = "contact", length = 20)
	@NotNull(message ="Contact Required")
	private String contact;

	@Column(name = "password_hash")
	@NotEmpty(message ="Password is Required")
	private String passwordHash;

	@Column(name = "email", unique = true)
	@NotEmpty(message ="Email is required")
	private String email;

	// One-to-Many relationship with Enrollment
	@OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Enrollment> enrollment;

	// getters and setters

	public int getLearnerId() {
		return learnerId;
	}

	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Enrollment> getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(List<Enrollment> enrollment) {
		this.enrollment = enrollment;
	}

	public Learner(String fullname, String gender, Date birthdate, String address, String contact, String passwordHash,
			String email, List<Enrollment> enrollment) {
		super();

		this.fullname = fullname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.address = address;
		this.contact = contact;
		this.passwordHash = passwordHash;
		this.email = email;
		this.enrollment = enrollment;
	}

	public Learner() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Learner [learnerId=" + learnerId + ", fullname=" + fullname + ", gender=" + gender + ", birthdate="
				+ birthdate + ", address=" + address + ", contact=" + contact + ", passwordHash=" + passwordHash
				+ ", email=" + email + ", enrollment=" + enrollment + "]";
	}

	public Learner( 
			 String email,String passwordHash) {
		super();
		this.passwordHash = passwordHash;
		this.email = email;
	}

	
}
