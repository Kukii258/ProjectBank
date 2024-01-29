package com.bank.projectbankv_01.Repositories;

import com.bank.projectbankv_01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Long> {
    Optional<User> findByNameAndPassword(String name,String password);
    Optional<User> findByEmail(String email);

}
