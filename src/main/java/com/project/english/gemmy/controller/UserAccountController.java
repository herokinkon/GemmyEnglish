package com.project.english.gemmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.english.gemmy.model.dto.ResponseModel;
import com.project.english.gemmy.model.dto.UserInfoRequest;
import com.project.english.gemmy.model.dto.UserInfoResponse;
import com.project.english.gemmy.service.StaffInfoService;
import com.project.english.gemmy.service.StudentInfoService;
import com.project.english.gemmy.util.MessageResponse;

@RestController
@RequestMapping("/user")
public class UserAccountController {
	
	@Autowired
	private StaffInfoService staffInfoService;
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	@GetMapping("/info")
	public ResponseEntity<ResponseModel> getUserAccount(UserInfoRequest userInfoRequest) {
		ResponseModel res = new ResponseModel();
		if (userInfoRequest == null || userInfoRequest.getUserName() == null
				|| userInfoRequest.getUserName().isEmpty()) {
			res.setMessage(MessageResponse.USER_INFO_ERROR);
			return new ResponseEntity<ResponseModel>(res, HttpStatus.BAD_REQUEST);
		}
		UserInfoResponse userInfoResult = new UserInfoResponse();
//		if (userInfoRequest.getTypeOfUser().equals("staff")) {
//			userInfoResult = staffInfoService.getUserInfoByUserAccountId(userInfoRequest.getUserId());
//		} else {
//			userInfoResult = studentInfoService.getUserInfoByUserAccountId(userInfoRequest.getUserId());
//		}
		if (userInfoResult == null) {
			res.setMessage(MessageResponse.USER_INFO_ERROR);
			return new ResponseEntity<ResponseModel>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		res.setMessage(MessageResponse.USER_INFO_SUCCESS);
		res.setBody(userInfoResult);
		return new ResponseEntity<ResponseModel>(res, HttpStatus.OK);
	}
}
