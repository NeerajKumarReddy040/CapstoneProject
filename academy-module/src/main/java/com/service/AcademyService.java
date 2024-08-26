package com.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Academy;
import com.repository.AcademyRepository;

@Service
public class AcademyService {
	Logger logger = LoggerFactory.getLogger(AcademyService.class);

	@Autowired
	AcademyRepository academyRepository;

	public Optional<Academy> getAcademyById(int id) {
		logger.info("academy details", academyRepository.findById(id));
		return academyRepository.findById(id);
	}

	public List<Academy> findAcademiesBySportName(String sportName) {

		return academyRepository.findAcademyBySportName(sportName);
	}
}
