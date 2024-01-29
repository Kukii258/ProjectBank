package com.bank.projectbankv_01.Repositories;

import com.bank.projectbankv_01.model.Transaction;
import com.bank.projectbankv_01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepositories extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderId(Long senderId);
    List<Transaction> findByReciverId(Long reciverId);
}
