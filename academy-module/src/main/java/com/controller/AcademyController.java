package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Academy;
import com.service.AcademyService;

@RestController
@RequestMapping("/academy")
@Validated
public class AcademyController {

	@Autowired
	AcademyService academyService;
	


	@GetMapping("/{academy_id}")
	public ResponseEntity<Academy> getAcademyById(@PathVariable("academy_id") int id) {
		Optional<Academy> academy = academyService.getAcademyById(id);
		if(academy.isPresent()) {
			return ResponseEntity.ok(academy.get());
		}else {
		return ResponseEntity.status(404).body(null);
		}
	}

	@GetMapping("/sport/{sportName}")
	public ResponseEntity<List<Academy>>  getAcademiesBySportName(@PathVariable("sportName") String sportName) {
		List<Academy> academyList= academyService.findAcademiesBySportName(sportName);
		if((academyList.isEmpty())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
			}else {
			return ResponseEntity.ok(academyList); 
			
		}
	}
}
