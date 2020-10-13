package com.tcs.dap40.loanservice.repository;

import com.tcs.dap40.loanservice.model.Loan;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanRepository extends MongoRepository<Loan, String> {
}