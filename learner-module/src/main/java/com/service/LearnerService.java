package com.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exceptions.CustomException;
import com.model.Academy;
import com.model.Learner;
import com.repository.LearnerRepository;

@Service
public class LearnerService {

	@Autowired
	LearnerRepository learnerRepository;

	@Autowired
	RestTemplate restTemplate;

	String baseUrl = "http://msacademy/academies";
	private static final Logger logger = LoggerFactory.getLogger(LearnerService.class);

	// Register
	public Learner addNewLearner(Learner learner) throws CustomException {
		logger.info("Accessed into addNewLearner method with input:", learner);
		Learner dblearner = learnerRepository.findByEmail(learner.getEmail());
		if(dblearner == null) {
		logger.debug("Checking if the email is present in database");
		try {
		Learner learnersave = learnerRepository.save(learner);
		return learnersave;
		}catch(Exception e) {  // if violation occures in db
		logger.error("The Details length has been exhausted");
			throw new CustomException("Problem in saving the record for Learner"); 
		}
		}
		else if (learner.getContact().length() > 10) {
	        logger.error("Phone number length exceeds 10 digits: {}", learner.getContact());
	         throw new CustomException("Phone number cannot be longer than 10 digits");
	        }
//		else if (learner.getBirthdate() != null) {
//	        LocalDate today = LocalDate.now();
//	        if (learner.getBirthdate().isAfter(today)) {
//	            logger.error("Date of birth is in the future: {}", birthdate);
//	            throw new CustomException("Date of birth cannot be in the future.");
//	        }
//	    }
		else {
		logger.error("The Verification Failed for the Learner email:{}", learner.getEmail());
			throw new CustomException("Already Email exits for Learner"); 
		}
	}

	// Login
	public String verify(Learner learner) throws CustomException {
		logger.info("Accessed into verify method with input:", learner);
		Learner credentials = learnerRepository.findByEmail(learner.getEmail());
		System.out.println("method called" + credentials);
		if (credentials != null && credentials.getPasswordHash().equals(learner.getPasswordHash())) {
			logger.info("The Verification was Successfull" , learner.getEmail());
			return ("You are Authenticated Successfully");
		}else {
			logger.error("Your details doesn't match the credentials in Database", learner.getEmail(),
					learner.getPasswordHash());
			throw new CustomException("Give proper email and password");
		}
		
	}

	// By SportName
	public List<Academy> getAcademyNamesBySportName(String sportName) {
		logger.info("Accessed into getAcademyNamesBySportName with input:", sportName);
		String url = baseUrl + "/sport/" + sportName;
		List<Academy> variable = (List<Academy>) restTemplate.getForObject(url, List.class);
		logger.info("Retriving the Acadmeies", variable);
		return variable;

	}

	// by ID
	public Academy getAcademyById(int id) throws CustomException {
		logger.info("Accessed into getAcademyById with input id:{}", id);
		String url = baseUrl + "/" + id;
		logger.info("Accessing the details of id", url);
        return restTemplate.getForObject(url, Academy.class);
	}

}
