package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Learner;
import com.repository.LearnerRepository;

@Service
public class LearnerService {

	@Autowired
	LearnerRepository learnerRepository;
 
	// Register
	public Learner addNewLearner(Learner learner) {
		Learner learner1 = learnerRepository.save(learner);
		return learner1;
	}
	
	public String verify(Learner learner) {
		System.out.println("method called" + learner.getEmail());
	    Learner credentials = learnerRepository.findByEmail(learner.getEmail());
	    System.out.println("method called" + credentials);
	    if (credentials != null && credentials.getPasswordHash().equals(learner.getPasswordHash())) {
	    	System.out.println("ok called");
	        return ("Credentails Matched");
	    }
	    return null;
	}

}
