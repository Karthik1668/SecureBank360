package com.karthik.securebank360.service;

import com.karthik.securebank360.entity.Loan;
import com.karthik.securebank360.entity.LoanStatus;
import com.karthik.securebank360.entity.User;
import com.karthik.securebank360.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan applyForLoan(User user, Double amount, String purpose) {
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Loan amount must be greater than zero");
        }
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setAmount(amount);
        loan.setPurpose(purpose);
        loan.setStatus(LoanStatus.PENDING);
        return loanRepository.save(loan);
    }

    @Override
    public Loan approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + loanId));
        loan.setStatus(LoanStatus.APPROVED);
        loan.setApprovedAt(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    @Override
    public Loan rejectLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + loanId));
        loan.setStatus(LoanStatus.REJECTED);
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getLoansByUser(User user) {
        return loanRepository.findAll().stream()
                .filter(l -> l.getUser().getId().equals(user.getId()))
                .toList();
    }

    @Override
    public List<Loan> getPendingLoans() {
        return loanRepository.findByStatus(LoanStatus.PENDING);
    }
}