package com.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exceptions.CustomException;
import com.model.Academy;
import com.service.AcademyService;

@RestController
@RequestMapping("/academies")
@Validated
public class AcademyController {

	@Autowired
	AcademyService academyService;
	private static final Logger logger = LoggerFactory.getLogger(AcademyController.class);


	@GetMapping("/{academy_id}")
	public ResponseEntity<Academy> getAcademyById(@PathVariable("academy_id") int id) throws CustomException {
		logger.info("Accessed into getAcademyById mehtod with :academyid{}", id);
		Optional<Academy> academy = academyService.getAcademyById(id);
		if (academy.isPresent()) {
			return ResponseEntity.ok(academy.get());
		} else {
			logger.error("Verification failed for Acadmey  in Database with academyId:"+ id);
			throw new CustomException("No Acadmey Record Found " + id);
		}
	}

	@GetMapping("/sport/{sportName}")
	public ResponseEntity<List<Academy>> getAcademiesBySportName(@PathVariable("sportName") String sportName)
			throws CustomException {
		logger.info("Accessed into getAcademiesBySportName mehtod with : sportName:{}", sportName);
		List<Academy> academyList = academyService.findAcademiesBySportName(sportName);
		if ((academyList.isEmpty())) {
			logger.error("Verficaiton failed for Sport Acadmey  in Database with :sportName:{}", sportName);
			throw new CustomException("No SportAcadmey Record Found for " + sportName);
		} else {
			
			return ResponseEntity.ok(academyList);

		}
	}
}
