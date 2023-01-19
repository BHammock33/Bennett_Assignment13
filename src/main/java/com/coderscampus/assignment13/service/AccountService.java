package com.coderscampus.assignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	UserService userServe;
	@Autowired
	UserRepository userRepo;

	public Account saveAccount(long userId) {
		User user = userServe.findById(userId);
		Account account = new Account();
		user.getAccounts().add(account);
		account.getUsers().add(user);
		account.setAccountName("Account:" + user.getAccounts().size());
		return accountRepo.save(account);
		
	}

	public Account findById(Long accountId) {
		Optional<Account> account = accountRepo.findById(accountId);
		return account.orElse(new Account());
	}
	public void saveAccount(Account account) {
		accountRepo.save(account);
	}

}
