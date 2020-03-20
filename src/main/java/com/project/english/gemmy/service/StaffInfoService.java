package com.project.english.gemmy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.repositories.StaffInfoRepository;
import com.project.english.gemmy.model.repositories.UserAccountRepository;
import com.project.english.gemmy.model.request.UpdateInfoRequest;
import com.project.english.gemmy.model.response.UserInfoResponse;

@Service
public class StaffInfoService {

	@Autowired
	private StaffInfoRepository staffInfoRepo;
	
	@Autowired
	private UserAccountRepository userAccountRepo;
	
	public UserInfoResponse getUserInfoByUserAccountId(Long userAccountId) {
		Optional<UserAccount> userAccount = userAccountRepo.findById(userAccountId);
		if (userAccount.isPresent()) {
			UserInfoResponse userInfoRes = new UserInfoResponse();
			List<StaffInfo> staffInfos = staffInfoRepo.findByUserAccount(userAccount.get());
			if (staffInfos != null && !staffInfos.isEmpty()) {
				userInfoRes.convertEntityToStaffObject(staffInfos.get(0));
				return userInfoRes;
			}
		}
		return null;
	}
	
	public boolean updateInfo(UpdateInfoRequest updateAccountRequest) {
		StaffInfo staffInfo = new StaffInfo();
		staffInfo.setId(updateAccountRequest.getUserId());
		staffInfo.setBirthday(updateAccountRequest.getBirthday());
		staffInfo.setContactNumber(updateAccountRequest.getContactNumber());
		staffInfo.setEmail(updateAccountRequest.getEmail());
		staffInfo.setFacebook(updateAccountRequest.getFacebook());
		staffInfo.setFullName(updateAccountRequest.getFullName());
		StaffInfo result = staffInfoRepo.save(staffInfo);
		if (result != null) {
			return true;
		}
		return false;
	}
}
