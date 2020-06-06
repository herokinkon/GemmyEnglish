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

import com.project.english.gemmy.model.dto.StudentDTO;
import com.project.english.gemmy.service.StudentInfoService;

@RestController
@RequestMapping("/api/student")
public class StudentInfoController {

	@Autowired
	private StudentInfoService studentInfoService;

	@GetMapping("/")
	public ResponseEntity<List<StudentDTO>> getAllStudent() {
		List<StudentDTO> studentInfoLst = studentInfoService.getAllStudent();
		if (studentInfoLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(studentInfoLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO updateInfoRequest) {
		StudentDTO result = studentInfoService.updateStudent(updateInfoRequest);
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentByStudentId(@PathVariable Long id) {
		StudentDTO result = studentInfoService.getStudentInfoById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/")
	public ResponseEntity<StudentDTO> createStudentInfo(@RequestBody StudentDTO updateInfoRequest) {
		StudentDTO studentInfo = studentInfoService.createNewStudent(updateInfoRequest);
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
	public ResponseEntity<List<StudentDTO>> getStudentListByClass(@RequestParam("classId") Long classId) {
		return ResponseEntity.ok().body(studentInfoService.getStudentListByClass(classId));
	}

	@GetMapping("/getStudentListByName")
	public ResponseEntity<List<StudentDTO>> searchStudentByStudentName(@RequestParam("name") String name) {
		List<StudentDTO> result = studentInfoService.getStudentListByName(name);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
		
	@GetMapping("/searchStudent")
	public ResponseEntity<List<StudentDTO>> searchStudent(@RequestParam String searchText) {
		List<StudentDTO> studentInfo = studentInfoService.getStudentInfoByName(searchText);
		if (studentInfo != null && !studentInfo.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(studentInfo);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
