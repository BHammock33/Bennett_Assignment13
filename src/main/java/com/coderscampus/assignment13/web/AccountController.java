package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accServe;
	@Autowired
	private UserService userServe;
	
	@PostMapping("users/{userId}/accounts")
	public String postAccount(@PathVariable Long userId) {
		accServe.saveAccount(userId);
		return "redirect:/users/"+userId;
	}
	@GetMapping("users/{userId}/accounts/{accountId}")
	public String getUserAccount(ModelMap model, @PathVariable Long accountId) {
		Account account = accServe.findById(accountId);
		User user = account.getUsers().get(0);
		model.put("account", account);
		model.put("user", user);
		return "account";
	}
	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String changeAccountName(@PathVariable Long userId, @PathVariable Long accountId, Account account) {
		account.setAccountName(account.getAccountName());
		accServe.saveAccount(account);
		userServe.saveUser(userServe.findById(userId));
		return "redirect:/users/"+userId+"/accounts/"+accountId;
	}
}
