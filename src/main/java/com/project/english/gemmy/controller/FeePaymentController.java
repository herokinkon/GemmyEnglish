package com.project.english.gemmy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.english.gemmy.model.dto.FeePaymentDTO;
import com.project.english.gemmy.service.FeePaymentService;

@RestController
@RequestMapping("/api/fee")
public class FeePaymentController {

	@Autowired
	private FeePaymentService paymentService;
	
	@PostMapping
	public void createPayment(@RequestBody FeePaymentDTO payment) {
		paymentService.createNewPayment(payment);
	}

	@GetMapping("/getAllPaymentInClass")
	public List<Map<String, String>> getPaymentsInClass(@RequestParam("classId") long classId) {
		return paymentService.getPaymentInClass(classId);
	}
	
	@GetMapping("/getAllPaymentForStudent")
	public Map<String, String> getPaymentsForStudent(@RequestParam("studentId") long studentId) {
		return paymentService.getPaymentForStudent(studentId);
	}

}
