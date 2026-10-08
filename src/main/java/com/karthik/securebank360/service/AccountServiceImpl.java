package com.karthik.securebank360.service;

import com.karthik.securebank360.entity.Account;
import com.karthik.securebank360.entity.AccountType;
import com.karthik.securebank360.entity.User;
import com.karthik.securebank360.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(User user, AccountType accountType) {
        Account account = new Account();
        account.setUser(user);
        account.setAccountType(accountType);
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setUpiId(generateUniqueUpiId(user));
        account.setBalance(0.0);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByUser(User user) {
        return accountRepository.findAll().stream()
                .filter(a -> a.getUser().getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No account found for this user"));
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            long number = 1000000000L + (long) (new Random().nextDouble() * 9000000000L);
            accountNumber = String.valueOf(number);
        } while (accountRepository.findByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }

    private String generateUniqueUpiId(User user) {
        String namePart = user.getFullName().toLowerCase().replaceAll("\\s+", "");
        String upiId;
        do {
            upiId = namePart + new Random().nextInt(9999) + "@securebank360";
        } while (accountRepository.findByUpiId(upiId).isPresent());
        return upiId;
    }
}