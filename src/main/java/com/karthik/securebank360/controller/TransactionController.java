package com.karthik.securebank360.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.securebank360.entity.Transaction;
import java.util.*;
import com.karthik.securebank360.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	
	
	
	private final TransactionService transactionService;
	TransactionController(TransactionService transactionService)
	{
		this.transactionService=transactionService;
	}
	
	
	
	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transfer(@RequestParam String fromAccountNumber,
			@RequestParam String toAccountNumber,
			@RequestParam Double amount,
			@RequestParam(required=false) String description){
		
		
		return ResponseEntity.ok(transactionService.transferFunds(fromAccountNumber, toAccountNumber, amount, description));
		
	}
	@GetMapping("/statement/{accountNumber}")
	public ResponseEntity<List<Transaction>> getStatement(@PathVariable String accountNumber)
	{
		return ResponseEntity.ok(transactionService.getRecentTransactions(accountNumber));
		
	}
	

}
