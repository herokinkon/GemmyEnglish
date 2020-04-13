package com.project.english.gemmy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.StudentDTO;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.repositories.StudentInfoRepository;
import com.project.english.gemmy.model.repositories.UserAccountRepository;
import com.project.english.gemmy.model.response.UserInfoResponse;

@Service
public class StudentInfoService {

	@Autowired
	private StudentInfoRepository studentInfoRepo;

	@Autowired
	private UserAccountRepository userAccountRepo;

	public List<StudentDTO> getAllStudent() {
		List<StudentInfo> students = studentInfoRepo.findAll();
		if (students != null && !students.isEmpty()) {
			return students.stream().map(StudentDTO::new).collect(Collectors.toList());
		}
		return null;
	}

	public StudentDTO getStudentInfoById(Long id) {
		Optional<StudentInfo> studentInfo = studentInfoRepo.findById(id);
		if (studentInfo.isPresent()) {
			ModelMapper modelMapper = new ModelMapper();
			StudentDTO result = modelMapper.map(studentInfo.get(), StudentDTO.class);
			return result;
		}
		return null;
	}

	public StudentDTO createNewStudent(StudentDTO updateInfoRequest) {
		ModelMapper modelMapper = new ModelMapper();
		StudentInfo studentInfo = modelMapper.map(updateInfoRequest, StudentInfo.class);
		StudentInfo result = studentInfoRepo.save(studentInfo);
		if (result != null) {
			return new StudentDTO(result);
		}
		return null;
	}

	// use for user
	public StudentDTO updateStudent(StudentDTO updateInfoRequest) {
		Optional<StudentInfo> temp = studentInfoRepo.findById(updateInfoRequest.getId());
		if (temp.isPresent()) {
			ModelMapper modelMapper = new ModelMapper();
			StudentInfo studentInfo = modelMapper.map(updateInfoRequest, StudentInfo.class);
			StudentInfo result = studentInfoRepo.save(studentInfo);
			if (result != null) {
				return new StudentDTO(result);
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

}
