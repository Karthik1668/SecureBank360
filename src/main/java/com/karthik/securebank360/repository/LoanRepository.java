package com.karthik.securebank360.repository;

import com.karthik.securebank360.entity.Loan;
import com.karthik.securebank360.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatus(LoanStatus status);
}