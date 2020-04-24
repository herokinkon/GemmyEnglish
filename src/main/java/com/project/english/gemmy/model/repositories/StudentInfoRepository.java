package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.model.jpa.UserAccount;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {

	List<StudentInfo> findByUserAccount(UserAccount userAccount);
	
	List<StudentInfo> findByClasses_id(long classId);
	
}
