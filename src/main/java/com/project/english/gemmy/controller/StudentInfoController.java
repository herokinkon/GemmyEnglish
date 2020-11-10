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
	public ResponseEntity<List<StudentDTO>> getAllStudent() throws Exception {
		List<StudentDTO> studentInfoLst = studentInfoService.getAllStudent();
		if (studentInfoLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(studentInfoLst);
		}
		throw new Exception("Student list is empty");
	}

	@PutMapping("/")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO updateInfoRequest) throws Exception {
		StudentDTO result = studentInfoService.updateStudent(updateInfoRequest);
		if (result != null) {
			return ResponseEntity.ok(result);
		}
		throw new Exception("Can not update student info");

	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentByStudentId(@PathVariable Long id) throws Exception {
		StudentDTO result = studentInfoService.getStudentInfoById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		throw new Exception(String.format("Student with id: %d is not found", id));
	}

	@PostMapping("/")
	public ResponseEntity<StudentDTO> createStudentInfo(@RequestBody StudentDTO updateInfoRequest) {
		StudentDTO studentInfo = studentInfoService.createNewStudent(updateInfoRequest);
		if (studentInfo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(studentInfo);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) throws Exception {
		try {
			studentInfoService.deleteStudent(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new Exception(String.format("Student with id: %d is not found", id));
		}
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

	@GetMapping("/getNewStudents")
	public ResponseEntity<List<StudentDTO>> getNewStudents() {
		List<StudentDTO> studentInfoLst = studentInfoService.getNewStudentList();
		if (studentInfoLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(studentInfoLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
