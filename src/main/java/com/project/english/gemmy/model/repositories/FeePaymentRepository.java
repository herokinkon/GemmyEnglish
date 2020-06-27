package com.project.english.gemmy.model.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.dto.FeePaymentDTO;
import com.project.english.gemmy.model.jpa.FeePayment;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Long> {
	List<FeePaymentDTO> findByClasses_id(long classId);

	List<FeePaymentDTO> findByStudentInfo_idAndClasses_status(long studentId, boolean isActive);

	List<FeePaymentDTO> findByStudentInfo_idAndClasses_id(long studentId, long classId);

	@Query(value = "select f.id, f.date, s.full_name, c.class_name, f.kind_of_payment, f.month, f.amount"
			+ " from fee_payment f, classes c, student_info s"
			+ " where f.classes_id = c.id and c.status = true and f.student_id = s.id", nativeQuery = true)
	List<Map<String, Object>> findByClasses_status();
	
}
