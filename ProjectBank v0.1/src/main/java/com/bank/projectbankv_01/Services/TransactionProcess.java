package com.bank.projectbankv_01.Services;

import com.bank.projectbankv_01.Repositories.TransactionRepositories;
import com.bank.projectbankv_01.model.User;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TransactionProcess {

    @Autowired
    TransactionRepositories transactionRepositories;

    private String amount;

    private String error;
    private String email;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId1() {
        return id1;
    }

    public void setId1(Long id) {
        this.id1 = id;
    }

    private Long id1;



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}
