package com.tcs.dap40.loanservice.controller;

import org.springframework.stereotype.Service;

import com.tcs.dap40.loanservice.model.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class Producer {
    @Value(value = "${message.topic.name}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, Loan> kafkaTemplate;

    public void sendMessage(Loan loan) {
        System.out.println("Sending TOPIC: "+TOPIC+" Message: "+loan);
        try {
            this.kafkaTemplate.send(TOPIC, loan);
            System.out.println("Successfuly Sent TOPIC: "+TOPIC+" Message: "+loan);
        } catch(Exception ex) {
            System.out.println("ERROR Sending TOPIC: "+TOPIC+" Message: "+loan+" Error Message: "+ex.getMessage());
        }
        
    }
}