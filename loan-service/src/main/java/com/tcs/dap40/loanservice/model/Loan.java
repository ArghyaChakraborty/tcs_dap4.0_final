package com.tcs.dap40.loanservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loan")
public class Loan {
    @Transient
    public static final String SEQUENCE_NAME = "loan_sequence";

    @Id
    private String loanId;
    private String borrowerDetails;
    private Double loanAmount;
    private Double loanDurationYears;
    private String mortgageDetails;

    public Loan() {
    }

    public Loan(String borrowerDetails, Double loanAmount, Double loanDurationYears, String mortgageDetails) {
        this.borrowerDetails = borrowerDetails;
        this.loanAmount = loanAmount;
        this.loanDurationYears = loanDurationYears;
        this.mortgageDetails = mortgageDetails;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBorrowerDetails() {
        return borrowerDetails;
    }

    public void setBorrowerDetails(String borrowerDetails) {
        this.borrowerDetails = borrowerDetails;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getLoanDurationYears() {
        return loanDurationYears;
    }

    public void setLoanDurationYears(Double loanDurationYears) {
        this.loanDurationYears = loanDurationYears;
    }

    public String getMortgageDetails() {
        return mortgageDetails;
    }

    public void setMortgageDetails(String mortgageDetails) {
        this.mortgageDetails = mortgageDetails;
    }

    @Override
    public String toString() {
        return "{\"loanId\": \""+loanId+"\", \"borrowerDetails\": \""+borrowerDetails+"\", \"loanAmount\": \""+loanAmount+"\", \"loanDurationYears\": \""+loanDurationYears+"\", \"mortgageDetails\": \""+mortgageDetails+"\"}";
    }



}