package com.tcs.dap40.loanservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loan")
public class Loan {
    @Transient
    public static final String SEQUENCE_NAME = "loan_sequence";

    @Id
    private Long loanId;
    private String borrowerDetails;
    private Double loanAmount;
    private Double loanDurationYears;
    private Double rateOfInterestPerAnnum;
    private String mortgageDetails;

    public Loan() {
    }

    public Loan(String borrowerDetails, Double loanAmount, Double loanDurationYears, Double rateOfInterestPerAnnum, String mortgageDetails) {
        this.borrowerDetails = borrowerDetails;
        this.loanAmount = loanAmount;
        this.loanDurationYears = loanDurationYears;
        this.rateOfInterestPerAnnum = rateOfInterestPerAnnum;
        this.mortgageDetails = mortgageDetails;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
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

    public Double getRateOfInterestPerAnnum() {
        return rateOfInterestPerAnnum;
    }

    public void setRateOfInterestPerAnnum(Double rateOfInterestPerAnnum) {
        this.rateOfInterestPerAnnum = rateOfInterestPerAnnum;
    }

    public String getMortgageDetails() {
        return mortgageDetails;
    }

    public void setMortgageDetails(String mortgageDetails) {
        this.mortgageDetails = mortgageDetails;
    }

    @Override
    public String toString() {
        return "{\"loanId\": \""+loanId+"\", \"borrowerDetails\": \""+borrowerDetails+"\", \"loanAmount\": \""+loanAmount+"\", \"loanDurationYears\": \""+loanDurationYears+"\", \"rateOfInterestPerAnnum\": \""+rateOfInterestPerAnnum+"\",  \"mortgageDetails\": \""+mortgageDetails+"\"}";
    }

}