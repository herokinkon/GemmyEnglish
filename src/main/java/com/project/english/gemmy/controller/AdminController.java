package com.project.english.gemmy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ResponseEntity<Void> createAccount() {
		return null;
	}
	
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public ResponseEntity<Void> updateAccount() {
		return null;
		
	}

}
