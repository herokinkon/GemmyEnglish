package com.project.english.gemmy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.english.gemmy.model.dto.CreateAccountRequest;
import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.repositories.UserAccountRepository;
import com.project.english.gemmy.util.CommonUtils;

@Service
public class UserAccountService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepo;

	@Transactional(rollbackFor = Exception.class)
	public boolean createUserAccount(CreateAccountRequest createAccountRequest) {
		UserAccount userAccount = new UserAccount();
		try {
			userAccount.setUserName(createAccountRequest.getUserName());
			userAccount.setJoinDate(CommonUtils.convertTime(System.currentTimeMillis()));
			userAccount.setPassword(createAccountRequest.getPassword());
			userAccount.setStatus(true);
			if (createAccountRequest.getTypeOfUSer().equals("staff")) {
				List<StaffInfo> staffInfos = new ArrayList<>();
				StaffInfo staffInfo = new StaffInfo();
				// staffInfo.setBirthday(createAccountRequest.getBirthday());
				staffInfo.setContactNumber(createAccountRequest.getContactNumber());
				staffInfo.setEmail(createAccountRequest.getContactNumber());
				staffInfo.setFacebook(createAccountRequest.getFacebook());
				staffInfo.setFullName(createAccountRequest.getFullName());
				staffInfos.add(staffInfo);
				userAccount.setStaffInfos(staffInfos);
			} else {
				List<StudentInfo> studentInfos = new ArrayList<>();
				StudentInfo studentInfo = new StudentInfo();
				// studentInfo.setBirthday(createAccountRequest.getBirthday());
				studentInfo.setContactNumber(createAccountRequest.getContactNumber());
				studentInfo.setEmail(createAccountRequest.getContactNumber());
				studentInfo.setFacebook(createAccountRequest.getFacebook());
				studentInfo.setFullName(createAccountRequest.getFullName());
				studentInfo.setParentContactNumber(createAccountRequest.getParentContactNumber());
				studentInfo.setParentEmail(createAccountRequest.getParentEmail());
				studentInfos.add(studentInfo);
				userAccount.setStudentInfos(studentInfos);
			}
			userAccount = userAccountRepo.save(userAccount);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return new User("admin", new BCryptPasswordEncoder().encode("admin"), new ArrayList<>());
		}
		throw new UsernameNotFoundException(username);
	}
}
