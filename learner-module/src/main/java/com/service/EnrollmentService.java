package com.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.controller.LearnerController;
import com.model.Academy;
import com.model.Enrollment;
import com.model.Learner;
import com.repository.EnrollmentRepository;
import com.repository.LearnerRepository;

import jakarta.validation.Valid;

@Service
public class EnrollmentService {

	@Autowired
	LearnerRepository learnerRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	
	@Autowired
	RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

	
	String baseUrl = "http://msacademy/academies";

	public String enrollLearner(String learnerId, String academyId, String enrollmentDate) {
		logger.info("entered");
		
			Enrollment enrollment = new Enrollment();
//			enrollment.setEnrollmentDate(enrollmentDate);
			String url = baseUrl + "/" + academyId;
			
			logger.info("url",url);
//			Academy academy = academyRepository.findById(academyId)
//					.orElseThrow(() -> new RuntimeException("Learner not found"));
			ResponseEntity<Academy> academy = restTemplate.getForEntity(url, Academy.class);
			
			enrollment.setAcademyId(academy.getBody().getAcademyId());

			
			Learner learner = learnerRepository.findById(Integer.getInteger(learnerId))
					.orElseThrow(() -> new RuntimeException("Learner not found"));
			enrollment.setLearner(learner);

			enrollmentRepository.save(enrollment);
			return " Your Enrollment successful";
		 
	}

	private boolean academyExists(int academyId) {
		String url = baseUrl + "/" + academyId; // Ensure URL path matches the actual endpoint
		try {

			return restTemplate.getForObject(url, Academy.class) != null; // We only care if the object exists

		} catch (HttpClientErrorException.NotFound e) {
			return false; // Academy not found
		} catch (Exception e) {
			throw new RuntimeException("Error checking if academy exists: " + e.getMessage());
		}
	}

}
