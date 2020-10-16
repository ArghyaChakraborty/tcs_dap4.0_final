package com.tcs.dap40.emiservice.repository;

import com.tcs.dap40.emiservice.model.EMILoan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EMILoanRepository extends JpaRepository<EMILoan, Long> {
    
}