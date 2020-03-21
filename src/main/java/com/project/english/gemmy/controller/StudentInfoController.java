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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.english.gemmy.model.request.UpdateInfoRequest;
import com.project.english.gemmy.model.response.StudentInfoResponse;
import com.project.english.gemmy.service.StudentInfoService;

@RestController
@RequestMapping("/student")
public class StudentInfoController {
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	@GetMapping("/")
	public ResponseEntity<List<StudentInfoResponse>> getAllStudent() {
		List<StudentInfoResponse> studentInfoLst = studentInfoService.getAllStudent();
		if (studentInfoLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(studentInfoLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentInfoResponse> updateStudent(@RequestBody UpdateInfoRequest updateInfoRequest) {
		StudentInfoResponse result = studentInfoService.updateStudent(updateInfoRequest);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}").buildAndExpand(result.getId()).toUri())
			.headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentInfoResponse> getStudentByStudentId(@PathVariable Long id) {
		StudentInfoResponse result = studentInfoService.getStudentInfoById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
//	@GetMapping("/{searchText}")
//	public ResponseEntity<List<StudentInfo>> searchStudentByName(@PathVariable String searchText) {
//		List<StudentInfo> studentInfoLst = studentInfoService.searchStudent(searchText);
//		if (studentInfoLst != null) {
//			HttpHeaders httpHeaders = new HttpHeaders();
//			return ResponseEntity.ok().headers(httpHeaders).body(studentInfoLst);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
	
	@PostMapping("/")
	public ResponseEntity<StudentInfoResponse> createStudentInfo(@RequestBody UpdateInfoRequest updateInfoRequest) {
		StudentInfoResponse studentInfo = studentInfoService.createNewStudent(updateInfoRequest);
		if (studentInfo != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}").buildAndExpand(studentInfo.getId()).toUri())
					.headers(httpHeaders).body(studentInfo);
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
}
