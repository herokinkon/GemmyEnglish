package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.dto.StaffDTO;
import com.project.english.gemmy.model.jpa.StaffInfo;
import com.project.english.gemmy.model.jpa.UserAccount;

@Repository
public interface StaffInfoRepository extends JpaRepository<StaffInfo, Long> {

	List<StaffInfo> findByUserAccount(UserAccount userAccount);
	List<StaffInfo> findByStaffType(String staffType);
	
	List<StaffDTO> findByFullNameContains(String name);
}
