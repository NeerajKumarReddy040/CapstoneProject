package com.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/learners")
@Validated
public class LearnerController {

//	@Autowired
//	LearnerService learnerservice;
	@GetMapping("/academynames")
	public ResponseEntity<Map<String,Object>> getAcademiesByAcademyName(@PathVariable String academyName) {
		return null;
	}
	
	@GetMapping("/academynames/sport/{sportname}") 
	public ResponseEntity<Map<String,Object>> getAcademyNamesBySportName(@PathVariable String sportName) {
		return null;
	}
	
	@GetMapping("/academies/{academy_id}") 
	public ResponseEntity<Map<String,Object>> getAcademyNamesByAcademyId(@PathVariable int id) {
		return null;
	}
	
	@GetMapping("/enrollments/{enrollment_id}")
	public ResponseEntity<Map<String,Object>> getAcademyNamesByEnrollemntId(@PathVariable int id) {
		return null;
	}
}
