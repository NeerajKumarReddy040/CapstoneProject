package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Enrollment;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment,Integer> {

}
