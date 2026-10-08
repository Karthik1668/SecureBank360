package com.karthik.securebank360.service;

import com.karthik.securebank360.entity.Loan;
import com.karthik.securebank360.entity.User;
import java.util.List;

public interface LoanService {
    Loan applyForLoan(User user, Double amount, String purpose);
    Loan approveLoan(Long loanId);
    Loan rejectLoan(Long loanId);
    List<Loan> getLoansByUser(User user);
    List<Loan> getPendingLoans();
}