package com.project.english.gemmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.english.gemmy.model.jpa.Exam;
import com.project.english.gemmy.model.jpa.ExamResult;
import com.project.english.gemmy.model.repositories.ExamRepository;
import com.project.english.gemmy.service.ExamResultService;
import com.project.english.gemmy.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ExamResultService examResultService;
	
	@Autowired
	private ExamRepository examRepo;

	@GetMapping("/")
	public ResponseEntity<List<Exam>> getAllExams() {
		List<Exam> exams = examService.getAllExams();
		if (exams != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(exams);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/")
	public ResponseEntity<Exam> createNewExam(@RequestBody Exam request) {
		Exam exam = examService.createNewExam(request);
		if (exam != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(exam);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/")
	public ResponseEntity<Exam> updateExam(@RequestBody Exam request) {
		Exam exam = examService.updateExam(request);
		if (exam != null) {
			return ResponseEntity.ok(exam);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExam(@PathVariable Long id) {
		boolean result = examService.deleteExam(id);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/searchExam")
	public ResponseEntity<List<Exam>> searchExam(@RequestParam String searchText) {
		List<Exam> exams = examRepo.findByNameContains(searchText);
		if (exams != null && !exams.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(exams);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/searchResult")
	public ResponseEntity<List<ExamResult>> getExamResult(@RequestParam("classId") String classId,
			@RequestParam("studentId") String studentId, @RequestParam("examId") String examId) {
		Long classIdNumber = !classId.isEmpty() ? Long.valueOf(classId) : null;
		Long studentIdNumber = !studentId.isEmpty() ? Long.valueOf(studentId) : null;
		Long examIdNumber = !examId.isEmpty() ? Long.valueOf(examId) : null;
		List<ExamResult> examResult = examResultService.getExamResult(classIdNumber, studentIdNumber, examIdNumber);
		if (examResult != null && !examResult.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(examResult);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getListResult")
	public ResponseEntity<List<ExamResult>> getListResult(@RequestParam("classId") String classId, 
			@RequestParam("examId") String examId) {
		System.out.println("getListResult");
		Long classIdNumber = !classId.isEmpty() ? Long.valueOf(classId) : null;
		Long examIdNumber = !examId.isEmpty() ? Long.valueOf(examId) : null;
		List<ExamResult> examResult = examResultService.getListResult(classIdNumber, null, examIdNumber);
		if (examResult != null && !examResult.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(examResult);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateResult")
	public ResponseEntity<List<ExamResult>> updateExamResult(@RequestBody ExamResult[] updateExams) {
		List<ExamResult> examResult = examResultService.updateExamResult(updateExams);
		if (examResult != null && !examResult.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(examResult);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
