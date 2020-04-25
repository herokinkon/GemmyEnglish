package com.project.english.gemmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.jpa.Exam;
import com.project.english.gemmy.model.repositories.ExamRepository;

@Service
public class ExamService {
	
	@Autowired
	private ExamRepository examRepo;

	public List<Exam> getAllExams() {
		List<Exam> allExams = examRepo.findAll();
		if (allExams != null && !allExams.isEmpty()) {
			return allExams;
		}
		return null;
	}
}
