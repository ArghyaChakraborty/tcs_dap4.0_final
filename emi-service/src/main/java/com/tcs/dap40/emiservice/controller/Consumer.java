package com.tcs.dap40.emiservice.controller;

import java.io.IOException;

import com.tcs.dap40.emiservice.model.EMILoan;
import com.tcs.dap40.emiservice.repository.EMILoanRepository;
import com.tcs.dap40.loanservice.model.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Consumer {
    @Autowired
    EMILoanRepository emiLoanRepository;

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);


    @KafkaListener(topics="${message.topic.name}", groupId="${spring.kafka.consumer.group-id}")
    public void receive(Loan loan) throws IOException {
        try {
            logger.debug("RECEIVED MESSAGE : "+loan);
            EMILoan emiLoan = new EMILoan(loan);
            emiLoanRepository.saveAndFlush(emiLoan);
            logger.debug("Saved EMI Loan of Id "+emiLoan.getLoanId());
        } catch(Exception ex) {
            logger.error("Unable to save EMI Loan of Id "+loan.getLoanId()+" Error : "+ex.getMessage());
            ex.printStackTrace();
        }
    }
}