package com.project.english.gemmy.service;

import java.util.List;
import java.util.Optional;

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
	
	public Exam createNewExam(Exam examInfo) {
		return examRepo.save(examInfo);
	}
	
	public Exam updateExam(Exam examInfo) {
		Optional<Exam> temp = examRepo.findById(examInfo.getId());
		if (temp.isPresent()) {
			temp.get().setName(examInfo.getName());
			temp.get().setExamType(examInfo.getExamType());
			temp.get().setDescription(examInfo.getDescription());
			return examRepo.save(temp.get());
		}
		return null;
	}
	
	public boolean deleteExam(Long id) {
		Optional<Exam> exam = examRepo.findById(id);
		if (exam.isPresent()) {
			try {
				examRepo.delete(exam.get());
				return true;
			} catch (Exception e){
				return false;
			}
		}
		return false;
	}
	
}
