package com.project.english.gemmy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassesController {
	
	@RequestMapping(value = "/getAllClasses", method = RequestMethod.GET)
	public ResponseEntity<Void> getAllClasses() {
		return null;
		
	}

	@RequestMapping(value = "/createClass", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewClass() {
		return null;
		
	}
	
	@RequestMapping(value = "/updateClass", method = RequestMethod.POST)
	public ResponseEntity<Void> updateClass() {
		return null;
		
	}
	
	@RequestMapping(value = "/getClassByName", method = RequestMethod.GET)
	public ResponseEntity<Void> getClassByName() {
		return null;
		
	}
	
	@RequestMapping(value = "/searchClass", method = RequestMethod.GET)
	public ResponseEntity<Void> searchClass() {
		return null;
		
	}

}
