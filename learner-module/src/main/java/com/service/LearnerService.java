package com.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.controller.LearnerController;
import com.model.Academy;
import com.model.Learner;
import com.repository.LearnerRepository;

import jakarta.validation.Valid;

@Service
public class LearnerService {

	@Autowired
	LearnerRepository learnerRepository;

	@Autowired
	RestTemplate restTemplate;

	String baseUrl = "http://msacademy/academies";
	private static final Logger logger = LoggerFactory.getLogger(LearnerService.class);

	// Register
	public Learner addNewLearner(Learner learner) {
		Learner learnersave = learnerRepository.save(learner);
		return learnersave;
	}

	// Login
	public String verify(Learner learner) {
		System.out.println("method called" + learner.getEmail());
		Learner credentials = learnerRepository.findByEmail(learner.getEmail());
		System.out.println("method called" + credentials);
		if (credentials != null && credentials.getPasswordHash().equals(learner.getPasswordHash())) {
			System.out.println("ok called");
			return ("You are Authenticated Successfully");
		}
		return null;
	}

	// By SportName
	public List<Academy> getAcademyNamesBySportName1(String sportName) {
		logger.info("entered in service");
		String url = baseUrl + "/sport/" + sportName;
		List<Academy> variable = (List<Academy>) restTemplate.getForObject(url, List.class);
		logger.info("retrived acadmeis1", variable);
		return variable;

	}

	// by ID
	public Academy getAcademyById(int id) {
		String url = baseUrl + "/" + id;
		return restTemplate.getForObject(url, Academy.class);
	}

}
