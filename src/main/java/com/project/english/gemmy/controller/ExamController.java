package com.project.english.gemmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.english.gemmy.model.dto.ClassesInfoDto;
import com.project.english.gemmy.model.jpa.Exam;
import com.project.english.gemmy.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
	
	@Autowired
	private ExamService examService;

	@GetMapping("/")
	public ResponseEntity<List<Exam>> getAllExams() {
		List<Exam> exams = examService.getAllExams();
		if (exams != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(exams);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/createExam", method = RequestMethod.POST)
	public ResponseEntity<Void> createExam() {
		return null;
	}
	
	@RequestMapping(value = "/updateExam", method = RequestMethod.POST)
	public ResponseEntity<Void> updateExam() {
		return null;
	}
	
	@RequestMapping(value = "/deleteExam", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteExam() {
		return null;
	}
}
