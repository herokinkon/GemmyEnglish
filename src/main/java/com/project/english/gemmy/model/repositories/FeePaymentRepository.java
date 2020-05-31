package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.dto.FeePaymentDTO;
import com.project.english.gemmy.model.jpa.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
	List<FeePaymentDTO> findByClasses_id(long classId);
	List<FeePaymentDTO> findByStudentInfo_idAndClasses_status(long classId, boolean isActive);
}
