package com.service;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exceptions.CustomException;
import com.model.Academy;
import com.model.Enrollment;
import com.model.Learner;
import com.repository.EnrollmentRepository;
import com.repository.LearnerRepository;

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

	public String enrollLearner(String learnerId, String academyId) {
		logger.info("entered enrollLearner method");

		Enrollment enrollment = new Enrollment();
		enrollment.setEnrollmentDate(new Date());
		String url = baseUrl + "/" + academyId;

		logger.info("url" + url);

		ResponseEntity<Academy> academy = restTemplate.getForEntity(url, Academy.class);
		logger.info((academy.getBody().toString()));

		enrollment.setAcademyId(Objects.requireNonNull(academy.getBody()).getAcademyId());

		Learner learner = learnerRepository.findById(Integer.valueOf(learnerId))
				.orElseThrow(() -> new RuntimeException("Learner not found"));
		enrollment.setLearnerId(learner.getLearnerId());

		enrollmentRepository.save(enrollment);
		return ("Your Enrollment successful");

	}

	 public Enrollment getEnrollmentById(int enrollmentId) throws CustomException {
	        return enrollmentRepository.findById(enrollmentId)
	            .orElseThrow(() -> new CustomException("No Enrollment Record Found for ID: " + enrollmentId));
	    }
	}

	


