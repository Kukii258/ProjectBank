package com.bank.projectbankv_01.Services;

import com.bank.projectbankv_01.Repositories.TransactionRepositories;
import com.bank.projectbankv_01.model.Contact;
import com.bank.projectbankv_01.model.Transaction;
import com.bank.projectbankv_01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServices {

    @Autowired
    TransactionRepositories transactionRepositories;
    @Autowired
    UserServices userServices;

    //function for transfering money by email
    public void transactionProcess(TransactionProcess transactionProcess){

        User user1 = (User) userServices.getById(transactionProcess.getId1());
        User user2 = (User) userServices.getByEmail(transactionProcess.getEmail());

        user1.setBalance(user1.getBalance()-Integer.parseInt(transactionProcess.getAmount()));
        user2.setBalance(user2.getBalance()+Integer.parseInt(transactionProcess.getAmount()));

        userServices.save(user1);
        userServices.save(user2);
        //saving that transaction in transaction dataBase
        Transaction transaction = new Transaction();
        transaction.setTransaction_date(LocalDateTime.now());
        transaction.setReciverId(user2.getId());
        transaction.setSenderId(user1.getId());
        transaction.setAmount(transactionProcess.getAmount());
        transaction.setTransactionType(user1.getName()+ " transfer $"+transaction.getAmount()+" to " + user2.getName());
        save(transaction);


    }
    //function for checking if written email is valid
    public Boolean isValidEmail(TransactionProcess transactionProcess){
        for (User user1 : userServices.getAllUsers()) {
            if (user1.getEmail().equals(transactionProcess.getEmail())) {
                return true;
            }
        }
        return false;
    }
    //puts all users transaction in list
    public List<Transaction> findAllByUserId(Long userId) {
        List<Transaction> senderTransactions = transactionRepositories.findBySenderId(userId);
        List<Transaction> receiverTransactions = transactionRepositories.findByReciverId(userId);

        // Combine sender and receiver transactions while avoiding duplicates
        Set<Transaction> allTransactions = new HashSet<>();
        allTransactions.addAll(senderTransactions);
        allTransactions.addAll(receiverTransactions);

        // Convert the set back to a list and sort it by date
        return allTransactions.stream()
                .sorted(Comparator.comparing(Transaction::getTransaction_date))
                .map(transaction -> {
                    // Add a minus sign for sender transactions
                    if (transaction.getSenderId().equals(userId)) {
                        transaction.setAmount("-" + transaction.getAmount());
                    }
                    return transaction;
                })
                .collect(Collectors.toList());
    }
    //Puts all User into getAllUsers list
    public List<Transaction> getAllTransaction(){return transactionRepositories.findAll();}

    //function for saving all user data into user object
    public void save(Transaction transaction){
        if(Objects.nonNull(transaction))
            transactionRepositories.save(transaction);
    }
    //deletes user object by its id
    public void delete(Long id){
        if(Objects.nonNull(id))
            transactionRepositories.deleteById(id);
    }
    //function for getting transaction by id
    public Transaction getById(Long id){
        Transaction transaction = null;
        if(Objects.nonNull(id)){
            Optional<Transaction> optionalUser = transactionRepositories.findById(id);
            if(optionalUser.isPresent()){
                transaction = optionalUser.get();
            }else{
                throw new RuntimeException("Transaction not found with the id " + id);
            }
        }
        return transaction;
    }

}
