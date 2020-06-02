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

import com.project.english.gemmy.model.dto.ClassesInfoDto;
import com.project.english.gemmy.model.dto.UpdateClassAttendanceRequest;
import com.project.english.gemmy.model.jpa.Course;
import com.project.english.gemmy.model.repositories.ClassesRepository;
import com.project.english.gemmy.service.ClassesService;
import com.project.english.gemmy.service.CourseService;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {

	@Autowired
	private ClassesService classesService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ClassesRepository classRepo;

	@GetMapping("/")
	public ResponseEntity<List<ClassesInfoDto>> getAllClasses() {
		List<ClassesInfoDto> classes = classesService.getAllClass();
		if (classes != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(classes);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassesInfoDto> getClassById(@PathVariable Long id) {
		ClassesInfoDto result = classesService.getClassById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/")
	public ResponseEntity<ClassesInfoDto> createNewClass(@RequestBody ClassesInfoDto request) {
		ClassesInfoDto classes = classesService.createNewClass(request);
		if (classes != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(classes);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/")
	public ResponseEntity<ClassesInfoDto> updateClass(@RequestBody ClassesInfoDto request) {
		ClassesInfoDto classes = classesService.updateClass(request);
		if (classes != null) {
			return ResponseEntity.ok(classes);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		boolean result = classesService.deleteClass(id);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getClassByStudent")
	public ResponseEntity<List<ClassesInfoDto>> getClassByStudent(@RequestParam("studentId") Long studentId) {
		return ResponseEntity.ok().body(classesService.getClassesByStudent(studentId));
	}
	
	@GetMapping("/getCourse")
	public ResponseEntity<List<Course>> getCourses() {
		List<Course> courses = courseService.getAllCourses();
		if (courses != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(courses);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/classAttendance")
	public ResponseEntity<?> updateClassAndAttendance(@RequestBody UpdateClassAttendanceRequest request) {
		boolean result = classesService.updateClassAndAttendance(request);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/attendance")
	public ResponseEntity<ClassesInfoDto> updateAttedance(@RequestBody UpdateClassAttendanceRequest request) {
		boolean result = classesService.updateAttedance(request.getClassInfo().getId(), request.getStudentInfo());
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/searchClass")
	public ResponseEntity<List<ClassesInfoDto>> searchClass(@RequestParam String searchText) {
		List<ClassesInfoDto> classInfos = classRepo.findByClassNameContains(searchText);
		if (classInfos != null && !classInfos.isEmpty()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(classInfos);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
