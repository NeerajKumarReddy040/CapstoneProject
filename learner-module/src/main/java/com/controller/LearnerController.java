package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dto.LearnerDTO;
import com.exceptions.CustomException;
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

	private static final Logger logger = LoggerFactory.getLogger(LearnerController.class);

	// register
	@PostMapping
	public ResponseEntity<?> register(@Valid @RequestBody Learner learner) {
		Learner register = new Learner();
		try {
			register = learnerService.addNewLearner(learner);
		} catch (Exception e) {
			return new ResponseEntity<>("The Learner with same registration details exists", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(register, HttpStatus.OK);
	}

	// login
	@PostMapping("/login")
	public ResponseEntity<?> loginCustomer(@Valid @RequestBody LearnerDTO learnerlogin) {
		Learner l1 = learnerlogin.toEnity();
		String customer = learnerService.verify(l1); // changed Learner to string
		if (customer != null) {
			return ResponseEntity.ok(customer);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Give proper email and password");
		}
	}

	// By SportName
	@GetMapping("/sports/{sportName}")
	public ResponseEntity<Map<String, Object>> getAcademyNamesBySportName1(
			@PathVariable("sportName") String sportName) {
		logger.info("entered");
		Map<String, Object> response = new HashMap(); // toprint response neat display
		List<Academy> academies = learnerService.getAcademyNamesBySportName1(sportName);
		if (academies.size() > 0) { // here size is method
			response.put("academeis", academies);
			response.put("status", "Success");
		} else {
			response.put("status", "Failes");
		}

		logger.info("retrived acadmeis", academies);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	// By AcademyId
	@GetMapping("/{academy_id}")
	public ResponseEntity<?> getAcademyById(@Valid @PathVariable("academy_id") int id) throws CustomException {
		Academy academy = learnerService.getAcademyById(id);
		if (academy != null) {
			return ResponseEntity.ok(academy);
		} else {
			throw new CustomException("No Academy Record Found For ID:" + id);

		}
	}
	




	@PostMapping(value = "/enroll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enroll(@RequestBody Map<String,Object> payload) {
		logger.info("entered into method::learnerId:{} ::academyId:{} ",payload.get("learnerId"),payload.get("academyId"));

	    try {
	    	logger.info("entered in to try");
	        String result = enrollmentService.enrollLearner(payload.get("learnerId").toString(),payload.get("academyId").toString(),payload.get("enrollmentDate").toString());
	        if ("Academy not found".equals(result)) {
	        	logger.info("entered into if");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	        }
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	    	logger.info("entered into catch block");
	        // Log the exception (optional)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
	    }
	}


//	@GetMapping("/enrollments/{enrollment_id}")
//	public ResponseEntity<Map<String,Object>> getAcademyNamesByEnrollemntId(@PathVariable int id) {
//		return null;
//	}
	

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	----------with resttemplate--------
	
/*	@GetMapping("/sport/{sportName}") 
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
	public ResponseEntity<?> getBookById(@Valid @PathVariable("academy_id") int Id) {
		String url = baseUrl + "/" + Id;

		ResponseEntity<Academy> academyid = restTemplate.getForEntity(url, Academy.class);
		if (academyid.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
			return academyid;
		} else if (academyid.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)) {
			return new ResponseEntity<String>("No Acadmey found", HttpStatus.NOT_FOUND);
		}
		return null;
	}
}*/
