package com.bank.projectbankv_01.Repositories;

import com.bank.projectbankv_01.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByFriend1(String friend1);
}
