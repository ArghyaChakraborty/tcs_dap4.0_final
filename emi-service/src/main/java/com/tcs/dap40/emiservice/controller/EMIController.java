package com.tcs.dap40.emiservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tcs.dap40.emiservice.model.EMI;
import com.tcs.dap40.emiservice.model.EMILoan;
import com.tcs.dap40.emiservice.repository.EMILoanRepository;
import com.tcs.dap40.emiservice.repository.EMIRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EMIController {
    @Autowired
    EMILoanRepository emiLoanRepository;
    @Autowired
    EMIRepository emiRepository;

    private static final Logger logger = LoggerFactory.getLogger(EMIController.class);

    @PostMapping("/emi/{loanId}")
    public ResponseEntity<String> payEMI(@PathVariable Long loanId, @RequestBody EMI emi) {
        ResponseEntity<String> response = null;
        try {
            Optional<EMILoan> optionalLoanObj = emiLoanRepository.findById(loanId);
            if(optionalLoanObj.isPresent()) {
                EMILoan loan = optionalLoanObj.get();
                emi.setLoan(loan);
                emi.setEmiPaymentDate(new Date());
                emi = emiRepository.saveAndFlush(emi);
                response = new ResponseEntity<>("Saving EMI for Loan Id "+loanId+" EMI Id: "+emi.getEmiId(), HttpStatus.CREATED);
            } else {
                throw new Exception("Loan with ID : "+loanId+" does not exist");
            }
        } catch(Exception ex) {
            logger.error("Exception saving EMI for loan id "+loanId+" Error: "+ex.getMessage());
            ex.printStackTrace();
            response = new ResponseEntity<>("Exception saving EMI for Loan id "+loanId+" Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }  

    @GetMapping("/emi/{loanId}/paid-emis")
    public ResponseEntity<Object> getPaidEMIs(@PathVariable Long loanId) {
        ResponseEntity<Object> response = null;
        try {
            Optional<EMILoan> optionalLoanObj = emiLoanRepository.findById(loanId);
            if(optionalLoanObj.isPresent()) {
                EMILoan loan = optionalLoanObj.get();
                response = new ResponseEntity<>(loan.getPaidEmis(), HttpStatus.OK); 
            } else {
                throw new Exception("Loan with ID : "+loanId+" does not exist");
            }
        } catch(Exception ex) {
            logger.error("Exception retrieving Paid EMIs for loan id "+loanId+" Error: "+ex.getMessage());
            ex.printStackTrace();
            response = new ResponseEntity<>("Exception retrieving Paid EMIs for loan id "+loanId+" Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       
        }
        return response;
    }

    @GetMapping("/emi/{loanId}/next-emi")
    public ResponseEntity<Object> getNextEMI(@PathVariable Long loanId) {
        ResponseEntity<Object> response = null;
        try {
            Optional<EMILoan> optionalLoanObj = emiLoanRepository.findById(loanId);
            if(optionalLoanObj.isPresent()) {
                EMILoan loan = optionalLoanObj.get();
                Double nextEMIAmount = getNextEMIAmount(loan);
                response = new ResponseEntity<>("Next EMI Amount for Loan Id: "+loanId+" : "+nextEMIAmount, HttpStatus.OK);
            } else {
                throw new Exception("Loan with ID : "+loanId+" does not exist");
            }
        } catch(Exception ex) {
            logger.error("Exception retrieving Paid EMIs for loan id "+loanId+" Error: "+ex.getMessage());
            ex.printStackTrace();
            response = new ResponseEntity<>("Exception retrieving Paid EMIs for loan id "+loanId+" Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       
        }
        return response;
    }

    @GetMapping("/emi/{loanId}/remaining-balance")
    public ResponseEntity<Object> getRemainingBalance(@PathVariable Long loanId) {
        ResponseEntity<Object> response = null;
        try {
            Optional<EMILoan> optionalLoanObj = emiLoanRepository.findById(loanId);
            if(optionalLoanObj.isPresent()) {
                EMILoan loan = optionalLoanObj.get();
                Double emiBalance = getEMIBalance(loan);
                response = new ResponseEntity<>("Remaining Balance for Loan Id: "+loanId+" : "+emiBalance, HttpStatus.OK);
            } else {
                throw new Exception("Loan with ID : "+loanId+" does not exist");
            }
        } catch(Exception ex) {
            logger.error("Exception retrieving Paid EMIs for loan id "+loanId+" Error: "+ex.getMessage());
            ex.printStackTrace();
            response = new ResponseEntity<>("Exception retrieving Paid EMIs for loan id "+loanId+" Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       
        }
        return response;
    }

    private Double getEMIBalance(EMILoan loan) {
        Double totalLoanAmt = loan.getLoanAmount();
        List<EMI> paidEMIs = loan.getPaidEmis();
        Double totalAmountPaid = 0.0;
        for (EMI emi : paidEMIs) {
            totalAmountPaid+= emi.getEmiPayment();
        }
        Double remainingAmount = totalLoanAmt - totalAmountPaid;
        if(remainingAmount <= 0) {
            remainingAmount = 0.0;
        }
        return remainingAmount;
    }

    private Double getNextEMIAmount(EMILoan loan) {
        Integer loanDurationMonths = (int) (loan.getLoanDurationYears() * 12);
        List<EMI> paidEMIs = loan.getPaidEmis();
        Integer numberOfRemainingInstallments = loanDurationMonths - paidEMIs.size();
        Double remainingAmount = getEMIBalance(loan);
        Double nextEMIAmount = 0.0;
        if(remainingAmount > 0) {
            nextEMIAmount = Math.ceil(remainingAmount/numberOfRemainingInstallments);
        }
        return nextEMIAmount;
    }

}