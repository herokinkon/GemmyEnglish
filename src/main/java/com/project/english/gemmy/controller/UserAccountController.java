package com.project.english.gemmy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAccountController {

	@RequestMapping(value = "/updateClass", method = RequestMethod.GET)
	public void createAcc() {
		
	}
}
