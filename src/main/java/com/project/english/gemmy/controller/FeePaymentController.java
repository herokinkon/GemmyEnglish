package com.project.english.gemmy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fee")
public class FeePaymentController {

	@RequestMapping(value = "/updateFeePayment", method = RequestMethod.POST)
	public ResponseEntity<Void> updateFeePayment() {
		return null;
	}
	
	@RequestMapping(value = "/getFeePayment", method = RequestMethod.GET)
	public ResponseEntity<Void> getFeePayment() {
		return null;
	}
	
	@RequestMapping(value = "/getFeePaymentByStudentName", method = RequestMethod.GET)
	public ResponseEntity<Void> getFeePaymentByStudentName() {
		return null;
	}
}
