package com.project.english.gemmy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
public class ExamController {

	@RequestMapping(value = "/getAllExam", method = RequestMethod.GET)
	public ResponseEntity<Void> getAllExam() {
		return null;
	}
	
	@RequestMapping(value = "/createExam", method = RequestMethod.POST)
	public ResponseEntity<Void> createExam() {
		return null;
	}
	
	@RequestMapping(value = "/updateExam", method = RequestMethod.POST)
	public ResponseEntity<Void> updateExam() {
		return null;
	}
	
	@RequestMapping(value = "/deleteExam", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteExam() {
		return null;
	}
}
