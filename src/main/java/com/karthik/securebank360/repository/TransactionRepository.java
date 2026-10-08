package com.karthik.securebank360.repository;

import com.karthik.securebank360.entity.Transaction;
import com.karthik.securebank360.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop10ByFromAccountOrToAccountOrderByTimestampDesc(Account fromAccount, Account toAccount);
    
}