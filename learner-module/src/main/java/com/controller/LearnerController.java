package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dto.LearnerDTO;
import com.model.Academy;
import com.model.Learner;
import com.service.EnrollmentService;
import com.service.LearnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/learners")
@Validated
public class LearnerController {

	@Autowired
	LearnerService learnerService;
	
	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	RestTemplate restTemplate;
	

	String baseUrl = "http://msacademy/academies";
	 
	// Register
	@PostMapping
	public ResponseEntity<?> Register(@Valid @RequestBody Learner learner)
	{
		Learner register = learnerService.addNewLearner(learner);
		return new ResponseEntity<>(register, HttpStatus.OK);
	}
	

	// login
	 @PostMapping("/login")
	    public ResponseEntity<?> loginCustomer(@Valid @RequestBody LearnerDTO learnerlogin) {
		 Learner l1 =learnerlogin.toEnity();
	        String customer = learnerService.verify(l1); //changed Learner to string
	        if (customer != null) {
	            return ResponseEntity.ok(customer);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Give proper email and password");
	        }
	    }
	
    // By SportName	 
	@GetMapping("/sport/{sportName}") 
	public ResponseEntity <?> getAcademyNamesBySportName(@PathVariable("sportName") String sportName) {
		System.out.println("called");
		String url = baseUrl+ "/sport/" + sportName;
		System.out.println(url);
		ResponseEntity<List> academies= restTemplate.getForEntity(url, List.class);
		if(academies.getStatusCode()==(HttpStatus.NO_CONTENT)) {
			return new ResponseEntity<>("No acadameis found with given sportName", HttpStatus.NOT_FOUND);
		
		} else if(academies.getStatusCode() == HttpStatus.OK) {
			return academies;
			
		}
		return null;
	}



	
	
	// By AcademyId
	@GetMapping("/{academy_id}") 
	public ResponseEntity<?>  getBookById(@Valid @PathVariable("academy_id") int Id){
		String url = baseUrl + "/"  + Id;
		
		 ResponseEntity<Academy> academyid =restTemplate.getForEntity(url,Academy.class);
		 if (academyid.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
				return academyid;
		} else if (academyid.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)) {
			return new ResponseEntity<String>("No Acadmey found", HttpStatus.NOT_FOUND);
			}
			return null;
		}
	
	
//	@GetMapping("/enrollments/{enrollment_id}")
//	public ResponseEntity<Map<String,Object>> getAcademyNamesByEnrollemntId(@PathVariable int id) {
//		return null;
//	}
}
