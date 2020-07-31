package com.project.english.gemmy.controller;

import java.util.List;
import java.util.Optional;

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

import com.project.english.gemmy.model.dto.OthersOutcomeDto;
import com.project.english.gemmy.model.dto.StaffDTO;
import com.project.english.gemmy.model.jpa.OthersOutcome;
import com.project.english.gemmy.model.repositories.OthersOutcomeRepository;
import com.project.english.gemmy.service.SalaryService;
import com.project.english.gemmy.service.StaffInfoService;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {
	
	@Autowired
	private StaffInfoService staffService;
	
	@Autowired
	private OthersOutcomeRepository othersOutcomeRepo;
	
	@Autowired
	private SalaryService salaryService;

	@GetMapping("/fulltime")
	public ResponseEntity<List<StaffDTO>> getFullTimeStaffSalary() {
		List<StaffDTO> result = staffService.getFullTimeStaffSalary();
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/parttime")
	public ResponseEntity<List<StaffDTO>> getPartTimeStaffSalary() {
		List<StaffDTO> result = staffService.getPartTimeStaffSalary();
		if (result != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	// othersoutcome
	@GetMapping("/othersoutcome")
	public ResponseEntity<List<OthersOutcomeDto>> getOthersOutcome() {
		List<OthersOutcomeDto> result = salaryService.getAllOthersOutcome();
		HttpHeaders httpHeaders = new HttpHeaders();
		return ResponseEntity.ok().headers(httpHeaders).body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OthersOutcome> getOtherOutcomeById(@PathVariable long id) {
		Optional<OthersOutcome> result = othersOutcomeRepo.findById(id);
		if (result.isPresent()) {
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result.get());
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<OthersOutcomeDto> updateOtherOutcome(@RequestBody OthersOutcomeDto otherOutcomeDto) {
		if (othersOutcomeRepo.existsById(otherOutcomeDto.getId())) {
			OthersOutcomeDto result = salaryService.createUpdateOtherOutcome(otherOutcomeDto, false);
			HttpHeaders httpHeaders = new HttpHeaders();
			return ResponseEntity.ok().headers(httpHeaders).body(result);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/")
	public ResponseEntity<OthersOutcomeDto> createOtherOutcome(@RequestBody OthersOutcomeDto otherOutcomeDto) {
		OthersOutcomeDto result = salaryService.createUpdateOtherOutcome(otherOutcomeDto, true);
		return new ResponseEntity<OthersOutcomeDto>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OthersOutcomeDto> removeOtherOutcome(@PathVariable long id) {
		Optional<OthersOutcome> otherOutcome = othersOutcomeRepo.findById(id);
		try {
			if (otherOutcome.isPresent()) {
				othersOutcomeRepo.deleteById(otherOutcome.get().getId());
			}
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
