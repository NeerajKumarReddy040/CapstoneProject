package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Learner;


@Repository
public interface LearnerRepository extends CrudRepository<Learner, Integer> {

	Learner findByEmail(String email);

	

}
