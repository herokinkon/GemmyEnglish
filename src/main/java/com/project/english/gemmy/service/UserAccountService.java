package com.project.english.gemmy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.english.gemmy.model.dto.AccountDTO;
import com.project.english.gemmy.model.jpa.UserAccount;
import com.project.english.gemmy.model.repositories.UserAccountRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepo;

	public List<AccountDTO> getAll() {
		return userAccountRepo.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
	}

	public AccountDTO getById(Long id) {
		Optional<UserAccount> result = userAccountRepo.findById(id);
		return result.isPresent() ? new AccountDTO(result.get()) : null;
	}

	public UserAccount create(AccountDTO account) {
		ModelMapper mapper = new ModelMapper();
		return userAccountRepo.save(mapper.map(account, UserAccount.class));
	}

	public void delete(Long id) {
		userAccountRepo.deleteById(id);
	}

	public void update(AccountDTO account) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		userAccountRepo.save(mapper.map(account, UserAccount.class));
	}
}
