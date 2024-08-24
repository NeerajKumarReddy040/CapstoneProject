package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.LearnerRepository;

@Service
public class EnrollmentService {

@Autowired
LearnerRepository learnerrepository;
}
