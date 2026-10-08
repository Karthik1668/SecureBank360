package com.karthik.securebank360.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.securebank360.entity.Account;
import com.karthik.securebank360.service.AccountService;




@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	
	private final AccountService accountService;
	AccountController(AccountService accountService){
		this.accountService=accountService;
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable String accountNumber)
	{
		Account account=accountService.getAccountByAccountNumber(accountNumber);
		return ResponseEntity.ok(account);
		
	}
	

}
