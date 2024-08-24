package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "coaching_academy_details")
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "academy_id")
	private int academyId;

	@Column(name = "academy_name", nullable = false, length = 255)
	private String academyName;

	@Column(name = "sport_name", nullable = false, length = 255)
	private String sportName;

	@Column(name = "address", nullable = false, length = 255)
	private String address;

	@Column(name = "contact_number", length = 20)
	private String contactNumber;

	@Column(name = "email_address", length = 255)
	private String emailAddress;

	@Column(name = "postcode", length = 10)
	private String postcode;

	// getters and setters
	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Academy(String academyName, String sportName, String address, String contactNumber, String emailAddress,
			String postcode) {
		super();
		this.academyName = academyName;
		this.sportName = sportName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.emailAddress = emailAddress;
		this.postcode = postcode;
	}

	public Academy() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Academy [academyId=" + academyId + ", academyName=" + academyName + ", sportName=" + sportName
				+ ", address=" + address + ", contactNumber=" + contactNumber + ", emailAddress=" + emailAddress
				+ ", postcode=" + postcode + "]";
	}
	
	
	
	

}
