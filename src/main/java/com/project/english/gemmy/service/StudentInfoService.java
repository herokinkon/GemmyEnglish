package com.project.english.gemmy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.StudentInfoDto;
import com.project.english.gemmy.model.dto.UpdateInfoRequest;
import com.project.english.gemmy.model.dto.UserInfoResponse;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.repositories.StudentInfoRepository;
import com.project.english.gemmy.model.repositories.UserAccountRepository;

@Service
public class StudentInfoService {

	@Autowired
	private StudentInfoRepository studentInfoRepo;

	@Autowired
	private UserAccountRepository userAccountRepo;

	public List<StudentInfoDto> getAllStudent() {
		List<StudentInfo> allUserAccount = studentInfoRepo.findAll();
		if (allUserAccount != null && !allUserAccount.isEmpty()) {
			List<StudentInfoDto> result = new ArrayList<>();
			allUserAccount.stream().forEach(item -> {
				StudentInfoDto temp = new StudentInfoDto(item);
				result.add(temp);
			});
			return result;
		}
		return null;
	}

	public StudentInfoDto getStudentInfoById(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			StudentInfoDto result = new StudentInfoDto(studentInfo.get());
			return result;
		}
		return null;
	}

	public StudentInfoDto createNewStudent(UpdateInfoRequest updateInfoRequest) {
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setBirthday(updateInfoRequest.getBirthday());
		studentInfo.setContactNumber(updateInfoRequest.getContactNumber());
		studentInfo.setEmail(updateInfoRequest.getEmail());
		studentInfo.setFacebook(updateInfoRequest.getFacebook());
		studentInfo.setFullName(updateInfoRequest.getFullName());
		studentInfo.setParentContactNumber(updateInfoRequest.getParentContactNumber());
		studentInfo.setParentEmail(updateInfoRequest.getParentEmail());
		StudentInfo result = studentInfoRepo.save(studentInfo);
		if (result != null) {
			return new StudentInfoDto(result);
		}
		return null;
	}

	// use for user
	public StudentInfoDto updateStudent(UpdateInfoRequest updateInfoRequest) {
		Optional<StudentInfo> temp = studentInfoRepo.findById(updateInfoRequest.getId());
		if (temp.isPresent()) {
			StudentInfo studentInfo = temp.get();
			studentInfo.setBirthday(updateInfoRequest.getBirthday());
			studentInfo.setContactNumber(updateInfoRequest.getContactNumber());
			studentInfo.setEmail(updateInfoRequest.getEmail());
			studentInfo.setFacebook(updateInfoRequest.getFacebook());
			studentInfo.setFullName(updateInfoRequest.getFullName());
			studentInfo.setParentContactNumber(updateInfoRequest.getParentContactNumber());
			studentInfo.setParentEmail(updateInfoRequest.getParentEmail());
			StudentInfo result = studentInfoRepo.save(studentInfo);
			if (result != null) {
				return new StudentInfoDto(result);
			}
		}
		return null;
	}

	public boolean deleteStudent(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			try {
				studentInfoRepo.delete(studentInfo.get());
				return true;
			} catch (Exception e) {
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
		studentInfo.setId(updateAccountRequest.getId());
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
	
	public List<StudentInfoDto> getStudentInfoByClass(Long id) {
		List<StudentInfo> allUserAccount = studentInfoRepo.findAll();
		if (allUserAccount != null && !allUserAccount.isEmpty()) {
			List<StudentInfoDto> result = new ArrayList<>();
			allUserAccount.stream().forEach(item -> {
				StudentInfoDto temp = new StudentInfoDto(item);
				result.add(temp);
			});
			return result;
		}
		return null;
	}
	
	public List<StudentInfo> getStudentListByClass(long classId){
		return studentInfoRepo.findByClasses_id(classId);
	}

}
