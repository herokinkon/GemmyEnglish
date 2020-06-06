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

import com.project.english.gemmy.model.dto.StaffDTO;
import com.project.english.gemmy.service.StaffInfoService;

@RestController
@RequestMapping("/api/staff")
public class StaffInfoController {

	@Autowired
	private StaffInfoService staffService;

	@GetMapping("/")
	public ResponseEntity<List<StaffDTO>> getAllStaff() {
		List<StaffDTO> staffInfoLst = staffService.getAllStaff();
		if (staffInfoLst != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(staffInfoLst);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StaffDTO> getStaffByStaffId(@PathVariable long id) {
		StaffDTO result = staffService.getStaffById(id);
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StaffDTO> removeStaffByStaffId(@PathVariable long id) {
		staffService.removeStaff(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping
	public ResponseEntity<StaffDTO> updateStaff(@RequestBody StaffDTO staff) {
		staffService.updateStaff(staff);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/")
	public ResponseEntity<StaffDTO> createStaffInfo(@RequestBody StaffDTO staff) {
		StaffDTO newStaff = staffService.createNewStaff(staff);
		return new ResponseEntity<StaffDTO>(newStaff, HttpStatus.CREATED);
	}
}
