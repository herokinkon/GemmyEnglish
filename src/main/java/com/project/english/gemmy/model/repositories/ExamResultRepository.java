package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.jpa.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

	@Query("SELECT e FROM #{#entityName} e WHERE (:classId is null or e.classes.id = :classId) and (:studentId is null"
			  + " or e.studentInfo.id = :studentId) and (:examId is null or e.exam.id = :examId)")
	List<ExamResult> findExamResultByStudentClassExam(@Param("classId") Long classId, 
			@Param("studentId") Long studentId, @Param("examId") Long examId);
}
