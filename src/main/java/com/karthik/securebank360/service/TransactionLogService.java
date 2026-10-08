package com.karthik.securebank360.service;

import com.karthik.securebank360.entity.*;
import com.karthik.securebank360.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionLogService {

    private final TransactionRepository transactionRepository;

    TransactionLogService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailedTransaction(Account fromAccount, Account toAccount, Double amount, String reason) {
        Transaction failedTxn = new Transaction();
        failedTxn.setFromAccount(fromAccount);
        failedTxn.setToAccount(toAccount);
        failedTxn.setAmount(amount);
        failedTxn.setType(TransactionType.TRANSFER);
        failedTxn.setStatus(TransactionStatus.FAILED);
        failedTxn.setDescription(reason);
        transactionRepository.save(failedTxn);
    }
}