package com.karthik.securebank360.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karthik.securebank360.entity.Account;
import com.karthik.securebank360.entity.Transaction;
import com.karthik.securebank360.entity.TransactionStatus;
import com.karthik.securebank360.entity.TransactionType;
import com.karthik.securebank360.repository.AccountRepository;
import com.karthik.securebank360.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;
    
    private final TransactionLogService transactionLogService;

	TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,TransactionLogService transactionLogService) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.transactionLogService=transactionLogService;
	}
 
	@Override
	@Transactional
	public Transaction transferFunds(String fromAccountNumber,String toAccountNumber,Double amount,String description) {
		if(fromAccountNumber.equals(toAccountNumber))
		{
			throw new RuntimeException("Cannnot Transfer to the same Account");
		}
		if(amount==0.0 || amount<=0)
		{
			throw new RuntimeException("Transfer amount must be greater than zero");
		}
		Optional<Account> fromAccountOptional=accountRepository.findByAccountNumber(fromAccountNumber);
		
		Account fromAccount = fromAccountOptional
		        .orElseThrow(() -> new RuntimeException(
		                "Source account not found: " + fromAccountNumber));
        Optional<Account>toAccountOptional=accountRepository.findByAccountNumber(toAccountNumber);
		
        Account toAccount = toAccountOptional
                .orElseThrow(() -> new RuntimeException(
                        "Destination account not found: " + toAccountNumber));
		
		if(fromAccount.getBalance()<amount)
		{
			transactionLogService.logFailedTransaction(fromAccount, toAccount, amount, "Failed: Insufficient Balance");
			throw new RuntimeException("Insufficient Balance");
		}
		else {
			fromAccount.setBalance(fromAccount.getBalance()-amount);
			toAccount.setBalance(toAccount.getBalance()+amount);
			accountRepository.save(fromAccount);
			accountRepository.save(toAccount);
			
			Transaction txn=new Transaction();
			txn.setAmount(amount);
			txn.setFromAccount(fromAccount);
			txn.setToAccount(toAccount);
			txn.setDescription(description);
			txn.setStatus(TransactionStatus.SUCCESS);
			txn.setType(TransactionType.TRANSFER);
			return transactionRepository.save(txn);
			
		}
		
		
		
	}


	
	
    @Override
	public List<Transaction> getRecentTransactions(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
        return transactionRepository.findTop10ByFromAccountOrToAccountOrderByTimestampDesc(account, account);
    }

}
