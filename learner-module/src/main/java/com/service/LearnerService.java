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
	
}
