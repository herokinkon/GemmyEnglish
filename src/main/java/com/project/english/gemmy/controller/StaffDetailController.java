package com.project.english.gemmy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffdetail")
public class StaffDetailController {

	@RequestMapping(value = "/getAllStaff", method = RequestMethod.GET)
	public ResponseEntity<Void> getAllStaff() {
		return null;
		
	}

	@RequestMapping(value = "/updateStaff", method = RequestMethod.POST)
	public ResponseEntity<Void> updateStaff() {
		return null;
		
	}
	
	@RequestMapping(value = "/getStaffByName", method = RequestMethod.GET)
	public ResponseEntity<Void> getStaffByName() {
		return null;
		
	}
	
	@RequestMapping(value = "/searchClass", method = RequestMethod.GET)
	public ResponseEntity<Void> searchClass() {
		return null;
		
	}
}
