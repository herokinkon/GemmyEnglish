package com.project.english.gemmy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/getAll")
	public List<Map<String, Object>> getAllPayment() {
		return paymentService.getAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<FeePaymentDTO> removePayment(@PathVariable long id) {
		paymentService.removePayment(id);
		return ResponseEntity.ok(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FeePaymentDTO> getPayment(@PathVariable long id) {
		return ResponseEntity.ok(paymentService.getPayment(id));
	}

	@PutMapping
	public ResponseEntity<FeePaymentDTO> updatePayment(@RequestBody FeePaymentDTO payment) {
		paymentService.updatePayment(payment);
		return ResponseEntity.ok(null);
	}

	@GetMapping("getAvailableMonth")
	public int getAvailableMonth(@RequestParam("studentId") long studentId, @RequestParam("classId") long classId) {
		paymentService.getAvailableMonth(long studentId, long classId);
	}
}
