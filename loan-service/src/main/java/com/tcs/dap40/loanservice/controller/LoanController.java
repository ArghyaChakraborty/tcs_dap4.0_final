package com.tcs.dap40.loanservice.controller;

import com.tcs.dap40.loanservice.model.DatabaseSequence;
import com.tcs.dap40.loanservice.model.Loan;
import com.tcs.dap40.loanservice.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanController {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;
    @Autowired
    private Producer producer;

    @GetMapping("/loan/")
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        ResponseEntity<List<Loan>> responseObj = null;
        responseObj = new ResponseEntity<>(loans, HttpStatus.OK);
        return responseObj;
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<Object> getLoanById(@PathVariable String loanId) {
        Optional<Loan> loanObjOptional = loanRepository.findById(loanId);
        ResponseEntity<Object> responseObj = null;
        if (loanObjOptional.isPresent()) {
            responseObj = new ResponseEntity<>(loanObjOptional.get(), HttpStatus.OK);
        } else {
            responseObj = new ResponseEntity<>("No Loan with id: "+loanId+" found", HttpStatus.NOT_FOUND);
        }
        return responseObj;
    }

    @PostMapping("/loan/")
    public ResponseEntity<String> saveLoan(@RequestBody Loan loan) {
        ResponseEntity<String> responseObj = null;
        try{
            loan.setLoanId(sequenceGenerator.generateSequence(Loan.SEQUENCE_NAME));
            Loan loanObj = loanRepository.save(loan);
            this.producer.sendMessage(loanObj);
            responseObj = new ResponseEntity<>("Saved Loan details with Id: "+loanObj.getLoanId(), HttpStatus.CREATED);
        } catch(Exception ex) {
            responseObj = new ResponseEntity<>("Failed to save Loan details :"+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObj;
    }

    @DeleteMapping("/loan/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable String loanId) {
        ResponseEntity<String> responseObj = null;
        try{
            loanRepository.deleteById(loanId);
            responseObj = new ResponseEntity<>("Deleted Loan details with Id: "+loanId, HttpStatus.OK);
        } catch(Exception ex) {
            responseObj = new ResponseEntity<>("Failed to delete Loan details :"+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseObj;
    }
}