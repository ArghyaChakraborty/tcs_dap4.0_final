package com.tcs.dap40.loanservice.controller;

import org.springframework.stereotype.Service;

import com.tcs.dap40.loanservice.model.Loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Producer {
    @Value(value = "${message.topic.name}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, Loan> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    public void sendMessage(Loan loan) {
        logger.debug("Sending TOPIC: "+TOPIC+" Message: "+loan);
        try {
            this.kafkaTemplate.send(TOPIC, loan);
            logger.debug("Successfuly Sent TOPIC: "+TOPIC+" Message: "+loan);
        } catch(Exception ex) {
            logger.error("ERROR Sending TOPIC: "+TOPIC+" Message: "+loan+" Error Message: "+ex.getMessage());
        }
        
    }
}