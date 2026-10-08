package com.karthik.securebank360.service;


import com.karthik.securebank360.entity.Transaction;
import java.util.List;


public interface TransactionService {

	Transaction transferFunds(String fromAccountNumber,String toAccountNumber,Double amount,String description);
	List<Transaction> getRecentTransactions(String accountNumber);
	
	
}
