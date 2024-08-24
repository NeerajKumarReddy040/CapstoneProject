package com.dto;

import com.model.Learner;

import jakarta.validation.constraints.NotEmpty;

public class LearnerDTO {
	
	@NotEmpty(message ="Email is required")
	private String email;
	
	@NotEmpty(message ="Password is Required")
	private String passwordHash;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public LearnerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LearnerDTO( String email,
			 String passwordHash) {
		super();
		this.email = email;
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "LearnerDTO [email=" + email + ", passwordHash=" + passwordHash + "]";
	}


	public  Learner toEnity() {
		return  new Learner(email, passwordHash);
		
	}
	
}
