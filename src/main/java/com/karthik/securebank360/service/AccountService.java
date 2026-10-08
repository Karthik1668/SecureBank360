package com.karthik.securebank360.service;

import com.karthik.securebank360.entity.Account;
import com.karthik.securebank360.entity.AccountType;
import com.karthik.securebank360.entity.User;

public interface AccountService {
      Account createAccount(User user,AccountType accountType);
      Account getAccountByUser(User user);
      Account getAccountByAccountNumber(String accountNumber);
}
