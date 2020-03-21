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

import com.project.english.gemmy.model.request.ClassRequest;
import com.project.english.gemmy.model.response.ClassesInfoResponse;
import com.project.english.gemmy.service.ClassesService;

@RestController
@RequestMapping("/classes")
public class ClassesController {
	
	@Autowired
	private ClassesService classesService;
	
	@GetMapping("/")
	public ResponseEntity<List<ClassesInfoResponse>> getAllClasses() {
		List<ClassesInfoResponse> classes = classesService.getAllClass();
		if (classes != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(classes);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<ClassesInfoResponse> getClassById(@PathVariable Long id) {
		ClassesInfoResponse result = classesService.getClassById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{searchText}")
	public ResponseEntity<Void> searchClassByName(@PathVariable String searchText) {
		return null;
	}
	
	@PostMapping("/")
	public ResponseEntity<ClassesInfoResponse> createNewClass(@RequestBody ClassRequest request) {
		ClassesInfoResponse classes = classesService.createNewClass(request);
		if (classes != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}").buildAndExpand(classes.getId()).toUri())
					.headers(httpHeaders).body(classes);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/")
	public ResponseEntity<ClassesInfoResponse> updateClass(@RequestBody ClassRequest request) {
		ClassesInfoResponse classes = classesService.updateClass(request);
		if (classes != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}").buildAndExpand(classes.getId()).toUri())
					.headers(httpHeaders).body(classes);
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

}
