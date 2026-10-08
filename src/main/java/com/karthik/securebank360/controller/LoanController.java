package com.karthik.securebank360.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.securebank360.entity.User;
import com.karthik.securebank360.entity.Loan;
import com.karthik.securebank360.service.LoanService;
import com.karthik.securebank360.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
	
	private final LoanService loanService;
	private final UserService userService;
	LoanController(LoanService loanService,UserService userService)
	{
		this.loanService=loanService;
		this.userService=userService;
	}
	
	@PostMapping("/apply")
	public ResponseEntity<Loan> applyForLoan(@RequestParam String email,
			@RequestParam Double amount,
			@RequestParam String purpose)
	{
		
		User user=userService.findByEmail(email);
		return ResponseEntity.ok(loanService.applyForLoan(user, amount, purpose));
		
		
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<Loan>> getPendingLoans()
	{
		return ResponseEntity.ok(loanService.getPendingLoans());
	}
	
	@PutMapping("/approve/{loanId}")
	public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId)
	{
		return ResponseEntity.ok(loanService.approveLoan(loanId));
	}
	
	@PutMapping("/reject/{loanId}")
	public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId)
	{
		return ResponseEntity.ok(loanService.rejectLoan(loanId));
	}
	
	@GetMapping("/loansByUser")
	public ResponseEntity<List<Loan>> getLoanByUser(@RequestParam String email)
	{
		User user=userService.findByEmail(email);
		return ResponseEntity.ok(loanService.getLoansByUser(user));
	}
	
	
	
	
	

}
