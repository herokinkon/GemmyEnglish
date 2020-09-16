package com.project.english.gemmy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.project.english.gemmy.model.dto.AccountDTO;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.service.UserAccountService;

@RestController
@RequestMapping("/api/account")
public class UserAccountController {

	@Autowired
	private UserAccountService service;

	@GetMapping()
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
		AccountDTO account = service.getById(id);
		return ResponseEntity.of(Optional.ofNullable(account));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping()
	public void update(@RequestBody AccountDTO account) {
		service.update(account);
	}

	@PostMapping
	public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO account){
		UserAccount newAccount = service.create(account);
		account.setId(newAccount.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(account);
	}

}
