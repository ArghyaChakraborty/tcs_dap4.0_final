package com.tcs.dap40.emiservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EMI {
    @Id
    @GeneratedValue
    private Integer emiId;
    private Date emiPaymentDate;
    private Double emiPayment;
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private EMILoan loan;

    public EMI() {
    }

    public EMI(Integer emiId, Date emiPaymentDate, Double emiPayment) {
        this.emiId = emiId;
        this.emiPaymentDate = emiPaymentDate;
        this.emiPayment = emiPayment;
    }

    public Integer getEmiId() {
        return emiId;
    }

    public void setEmiId(Integer emiId) {
        this.emiId = emiId;
    }

    public Date getEmiPaymentDate() {
        return emiPaymentDate;
    }

    public void setEmiPaymentDate(Date emiPaymentDate) {
        this.emiPaymentDate = emiPaymentDate;
    }

    public Double getEmiPayment() {
        return emiPayment;
    }

    public void setEmiPayment(Double emiPayment) {
        this.emiPayment = emiPayment;
    }

    public EMILoan getLoan() {
        return loan;
    }

    public void setLoan(EMILoan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "{\"emiId\": \""+this.emiId+"\", \"emiPaymentDate\": \""+this.emiPaymentDate+"\", \"emiPayment\": \""+this.emiPayment+"\"}";
    }
}