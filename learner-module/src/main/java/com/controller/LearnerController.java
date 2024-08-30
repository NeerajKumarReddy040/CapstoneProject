package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.dto.LearnerDTO;
import com.exceptions.CustomException;
import com.model.Academy;
import com.model.Enrollment;
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

	private static final Logger logger = LoggerFactory.getLogger(LearnerController.class);

	// register
	@PostMapping
	public ResponseEntity<?> register(@Valid @RequestBody Learner learner) throws CustomException {
		logger.info("Accessed into register method with input:{}", learner);
		Learner register = new Learner();
		register = learnerService.addNewLearner(learner);
		logger.info("Your Registration Successfull");
		return new ResponseEntity<>(register, HttpStatus.OK);
	}

	// login
	@PostMapping("/login")
	public ResponseEntity<?> loginCustomer(@Valid @RequestBody LearnerDTO learnerlogin) throws CustomException {
		logger.info("Accessed into loginCustomer mehtod with input:{}", learnerlogin);
		Learner l1 = learnerlogin.toEnity();
		String customer = learnerService.verify(l1); // changed Learner to string
		if (customer != null) {
			logger.info("Your Email is verified");
			return ResponseEntity.ok(customer);
		}
		return null;

	}

	// By SportName
	@GetMapping("/sports/{sportName}")
	public ResponseEntity<Map<String, Object>> getAcademyNamesBySportName(@PathVariable("sportName") String sportName)
			throws CustomException {
		logger.info("Controller:Entered into getAcademyNamesBySportName method with input: sportName:{}", sportName);
		Map<String, Object> response = new HashMap();// toprint response neat display
		try {

			List<Academy> academies = learnerService.getAcademyNamesBySportName(sportName);
			if (academies.size() > 0) { // here size is method
				response.put("academeis", academies);
				response.put("status", "Success");
			} else {
				response.put("status", "Failes");
			}

			logger.info("retrived acadmeis", academies);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("The  Sport Acadmey does not match in the database:{}", sportName);
			throw new CustomException("No Sport Academy Found for SportName:{} " + sportName);
		}
	}


	// By AcademyId
	@GetMapping("/{academy_id}")
	public ResponseEntity<?> getAcademyById(@PathVariable("academy_id") @Valid int id) throws CustomException {
		logger.info("Entered into getAcademyById method with input: acadmeyId:{}", id);
		try {

			Academy academy = learnerService.getAcademyById(id);
			if (academy != null) {
				return ResponseEntity.ok(academy);
			}
		} catch (Exception e) {
			logger.error("The Acadmey does not match in the database", id);
			throw new CustomException("No Academy Found for AcademyId:{} " + id);
		}
		return null;
	}

	// enroll
	@PostMapping(value = "/enroll")
	public ResponseEntity<String> enroll( @RequestBody Map<String, Object> payload) throws CustomException {
		logger.info("Contoller:entered into method::learnerId:{} ::academyId:{} ", payload.get("learnerId"),
				payload.get("academyId"));
		if (payload.get("academyId") == null) {
			throw new CustomException("academyId is not present in the request");
		}
		if (payload.get("learnerId") == null) {
			throw new CustomException("learnerid is not present in the request");
		}
		if (payload.get("learnerId").toString().isEmpty()) {
			throw new CustomException("learnerid is Empty");
		}
		if (payload.get("academyId").toString().isEmpty()) {
			throw new CustomException("academyId is Empty");
		}

		try {
			logger.info("entered into try");
			String result = enrollmentService.enrollLearner(payload.get("learnerId").toString(),
					payload.get("academyId").toString());
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			logger.error("The given data not present in Database");
			throw new CustomException("No data found");

		}

	}

	// Enrollements
	@GetMapping("/enrollments/{enrollment_id}")
    public ResponseEntity<Enrollment> getEnrollmentsById(@PathVariable("enrollment_id") int enrollmentId) throws CustomException {
        logger.info("Entered getEnrollmentsById method with enrollmentId: {}", enrollmentId);
        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        logger.info("Enrollment record found for ID: {}", enrollmentId);
        return ResponseEntity.ok(enrollment);
    }

}
