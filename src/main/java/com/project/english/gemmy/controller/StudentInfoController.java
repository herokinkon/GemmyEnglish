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

import com.project.english.gemmy.model.dto.StudentInfoDto;
import com.project.english.gemmy.model.dto.UpdateInfoRequest;
import com.project.english.gemmy.model.jpa.StudentInfo;
import com.project.english.gemmy.service.StudentInfoService;

@RestController
@RequestMapping("/api/student")
public class StudentInfoController {
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	@GetMapping("/")
	public ResponseEntity<List<StudentInfoDto>> getAllStudent() {
		List<StudentInfoDto> studentInfoLst = studentInfoService.getAllStudent();
		if (studentInfoLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(studentInfoLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/")
	public ResponseEntity<StudentInfoDto> updateStudent(@RequestBody UpdateInfoRequest updateInfoRequest) {
		StudentInfoDto result = studentInfoService.updateStudent(updateInfoRequest);
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentInfoDto> getStudentByStudentId(@PathVariable Long id) {
		StudentInfoDto result = studentInfoService.getStudentInfoById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/")
	public ResponseEntity<StudentInfoDto> createStudentInfo(@RequestBody UpdateInfoRequest updateInfoRequest) {
		StudentInfoDto studentInfo = studentInfoService.createNewStudent(updateInfoRequest);
		if (studentInfo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(studentInfo);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		boolean result = studentInfoService.deleteStudent(id);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getStudentListByClass")
	public ResponseEntity<List<StudentInfo>> getStudentListByClass(@RequestParam("classId") Long classId) {
		System.out.println("Receive request to get Student List by class");
		return ResponseEntity.ok().body(studentInfoService.getStudentListByClass(classId));
	}
}
