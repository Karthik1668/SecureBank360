package com.karthik.securebank360.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.securebank360.entity.Account;


public interface AccountRepository extends JpaRepository<Account,Long> {

	Optional<Account> findByAccountNumber(String AccountNumber);
	
	Optional<Account> findByUpiId(String upiId);
}
