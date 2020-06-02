package com.project.english.gemmy.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.english.gemmy.model.jpa.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

	List<Exam> findByNameContains(String name);
}
