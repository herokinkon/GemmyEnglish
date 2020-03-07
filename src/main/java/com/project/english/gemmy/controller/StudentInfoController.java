package com.project.english.gemmy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentInfoController {

	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET)
	public ResponseEntity<Void> getAllStudent() {
		return null;
		
	}

	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public ResponseEntity<Void> updateStudent() {
		return null;
		
	}
	
	@RequestMapping(value = "/getStudentByName", method = RequestMethod.GET)
	public ResponseEntity<Void> getStudentByName() {
		return null;
		
	}
	
	@RequestMapping(value = "/searchStudent", method = RequestMethod.GET)
	public ResponseEntity<Void> searchStudent() {
		return null;
		
	}
}
