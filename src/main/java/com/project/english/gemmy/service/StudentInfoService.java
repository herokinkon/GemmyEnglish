package com.project.english.gemmy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.repositories.StudentInfoRepository;
import com.project.english.gemmy.model.repositories.UserAccountRepository;
import com.project.english.gemmy.model.request.UpdateInfoRequest;
import com.project.english.gemmy.model.response.UserInfoResponse;

@Service
public class StudentInfoService {

	@Autowired
	private StudentInfoRepository studentInfoRepo;
	
	@Autowired
	private UserAccountRepository userAccountRepo;
	
	public List<StudentInfo> getAllStudent() {
		List<StudentInfo> allUserAccount = studentInfoRepo.findAll();
		if (allUserAccount != null && !allUserAccount.isEmpty()) {
			return allUserAccount;
		}
		return null;
	}
	
	public StudentInfo getStudentInfoById(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			return studentInfo.get();
		}
		return null;
	}
	
	// use for user
	public StudentInfo updateStudent(UpdateInfoRequest updateInfoRequest) {
		StudentInfo studentInfo = new StudentInfo();
		if (updateInfoRequest.getUserId() != null) {
			studentInfo.setId(updateInfoRequest.getUserId());
		}
		studentInfo.setBirthday(updateInfoRequest.getBirthday());
		studentInfo.setContactNumber(updateInfoRequest.getContactNumber());
		studentInfo.setEmail(updateInfoRequest.getEmail());
		studentInfo.setFacebook(updateInfoRequest.getFacebook());
		studentInfo.setFullName(updateInfoRequest.getFullName());
		studentInfo.setParentContactNumber(updateInfoRequest.getParentContactNumber());
		studentInfo.setParentEmail(updateInfoRequest.getParentEmail());
		StudentInfo result = studentInfoRepo.save(studentInfo);
		if (result != null) {
			return result;
		}
		return null;
	}
	
	public boolean deleteStudent(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			try {
				studentInfoRepo.delete(studentInfo.get());
				return true;
			} catch (Exception e){
				return false;
			}
		}
		return false;
	}
	
	public UserInfoResponse getUserInfoByUserAccountId(Long userAccountId) {
		Optional<UserAccount> userAccount = userAccountRepo.findById(userAccountId);
		if (userAccount.isPresent()) {
			UserInfoResponse userInfoRes = new UserInfoResponse();
			List<StudentInfo> studentInfos = studentInfoRepo.findByUserAccount(userAccount.get());
			if (studentInfos != null && !studentInfos.isEmpty()) {
				userInfoRes.convertEntityToStudentObject(studentInfos.get(0));
				return userInfoRes;
			}
		}
		return null;
	}
	
	// use for admin
	public boolean updateInfo(UpdateInfoRequest updateAccountRequest) {
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setId(updateAccountRequest.getUserId());
		studentInfo.setBirthday(updateAccountRequest.getBirthday());
		studentInfo.setContactNumber(updateAccountRequest.getContactNumber());
		studentInfo.setEmail(updateAccountRequest.getEmail());
		studentInfo.setFacebook(updateAccountRequest.getFacebook());
		studentInfo.setFullName(updateAccountRequest.getFullName());
		StudentInfo result = studentInfoRepo.save(studentInfo);
		if (result != null) {
			return true;
		}
		return false;
	}
	
}
