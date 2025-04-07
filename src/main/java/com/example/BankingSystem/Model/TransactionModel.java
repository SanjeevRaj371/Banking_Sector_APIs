package com.example.BankingSystem.Model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TransactionModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private BigDecimal transactionAmount;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountModel accountModel;
    private String transactionType;
    private String Describtion;
    private LocalDateTime transactionTime;
    private  BigDecimal Balance;

    public String getDescribtion() {
        return Describtion;
    }

    public void setDescribtion(String describtion) {
        Describtion = describtion;
    }


    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
