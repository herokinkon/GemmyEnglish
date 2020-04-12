package com.project.english.gemmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.english.gemmy.model.dto.CreateAccountRequest;
import com.project.english.gemmy.model.dto.ResponseModel;
import com.project.english.gemmy.model.dto.UpdateInfoRequest;
import com.project.english.gemmy.service.StaffInfoService;
import com.project.english.gemmy.service.StudentInfoService;
import com.project.english.gemmy.service.UserAccountService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private StaffInfoService staffInfoService;
	
	@Autowired
	private StudentInfoService studentInfoService;

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ResponseEntity<ResponseModel> createAccount(CreateAccountRequest createAccountRequest) {
		boolean isSuccess = userAccountService.createUserAccount(createAccountRequest);
		if (isSuccess) {
			ResponseModel res = new ResponseModel();
			res.setMessage("");
			return new ResponseEntity<ResponseModel>(res, HttpStatus.OK);
		}
		ResponseModel res = new ResponseModel();
		res.setMessage("Could not create User Account");
		return new ResponseEntity<ResponseModel>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public ResponseEntity<ResponseModel> updateAccount(UpdateInfoRequest updateAccountRequest) {
		boolean isSuccess;
		if (updateAccountRequest.getTypeOfUSer().equals("staff")) {
			isSuccess = staffInfoService.updateInfo(updateAccountRequest);
		} else {
			isSuccess = studentInfoService.updateInfo(updateAccountRequest);
		}
		if (isSuccess) {
			ResponseModel res = new ResponseModel();
			res.setMessage("");
			return new ResponseEntity<ResponseModel>(res, HttpStatus.OK);
		}
		ResponseModel res = new ResponseModel();
		res.setMessage("Could not create User Account");
		return new ResponseEntity<ResponseModel>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
