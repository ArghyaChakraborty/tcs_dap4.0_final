package com.tcs.dap40.emiservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tcs.dap40.loanservice.model.Loan;

import java.util.LinkedList;

@Entity
public class EMILoan {
    @Id
    private Long loanId;
    private String borrowerDetails;
    private Double loanAmount;
    private Double loanDurationYears;
    private Double rateOfInterestPerAnnum;
    private String mortgageDetails;
    @OneToMany(mappedBy="loan", fetch=FetchType.LAZY)
    private List<EMI> paidEmis;

    public EMILoan() {
        this.paidEmis = new LinkedList<EMI>();
    }

    public EMILoan(Long loanId, String borrowerDetails, Double loanAmount, Double loanDurationYears, Double rateOfInterestPerAnnum, String mortgageDetails) {
        this();
        this.loanId = loanId;
        this.borrowerDetails = borrowerDetails;
        this.loanAmount = loanAmount;
        this.loanDurationYears = loanDurationYears;
        this.rateOfInterestPerAnnum = rateOfInterestPerAnnum;
        this.mortgageDetails = mortgageDetails;
    }

    public EMILoan(Loan loan) {
        this.loanId = loan.getLoanId();
        this.borrowerDetails = loan.getBorrowerDetails();
        this.loanAmount = loan.getLoanAmount();
        this.loanDurationYears = loan.getLoanDurationYears();
        this.rateOfInterestPerAnnum = loan.getRateOfInterestPerAnnum();
        this.mortgageDetails = loan.getMortgageDetails();
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

    /*public void addNewEmiPayment(EMI payment) {
        this.paidEmis.add(payment);
    }*/

    public List<EMI> getPaidEmis() {
        return paidEmis;
    }

    public void setPaidEmis(List<EMI> paidEmis) {
        this.paidEmis = paidEmis;
    }

    @Override
    public String toString() {
        return "{\"loanId\": \""+loanId+"\", \"borrowerDetails\": \""+borrowerDetails+"\", \"loanAmount\": \""+loanAmount+"\", \"loanDurationYears\": \""+loanDurationYears+"\", \"rateOfInterestPerAnnum\": \""+rateOfInterestPerAnnum+"\",  \"mortgageDetails\": \""+mortgageDetails+"\", \"numberOfPaidEMIs\": \""+this.paidEmis.size()+"\"}";
    }

}