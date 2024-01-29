package com.bank.projectbankv_01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    public Transaction(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private Long reciverId;
    private String amount;
    private LocalDateTime transaction_date;
    private String transactionType;

    public Long getReciverId() {
        return reciverId;
    }
    public void setReciverId(Long reciverId) {
        this.reciverId = reciverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDateTime transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", transaction_date=" + transaction_date +
                ", transactionType=" + transactionType +
                '}';
    }

}
