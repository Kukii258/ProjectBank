package com.bank.projectbankv_01.Services;

import com.bank.projectbankv_01.Repositories.UserRepositories;
import com.bank.projectbankv_01.model.Contact;
import com.bank.projectbankv_01.model.Transaction;
import com.bank.projectbankv_01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepositories userRepositories;

    //Puts all User into getAllUsers list
    public List<User> getAllUsers(){return userRepositories.findAll();}

    //function for saving all user data into user database
    public void save(User user){
        if(Objects.nonNull(user))
            userRepositories.save(user);
    }

    //deletes user object by its id
    public void delete(Long id){
        if(Objects.nonNull(id))
            userRepositories.deleteById(id);
    }
    //get user by id
    public User getById(Long id){
        User user = null;
        if(Objects.nonNull(id)){
            Optional<User> optionalUser = userRepositories.findById(id);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }else{
                throw new RuntimeException("Employe not found with the id " + id);
            }
        }
        return user;
    }
    //get user by email
    public User getByEmail(String email){
        User user = null;
        if(Objects.nonNull(email)){
            Optional<User> optionalUser = userRepositories.findByEmail(email);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }else{
                throw new RuntimeException("Email not found with id " + email);
            }
        }
        return user;
    }
    //function for registering user
    public User registerUser(String name,String password, String email, String age, String adress, String phoneNumber){
        if(name == null || password == null || email == null){
            return null;
        }else {
            //checking if the email is unique
            if(userRepositories.findByEmail(email).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            //creates new user
            User user = new User();
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setAdress(adress);
            user.setAge(age);
            user.setPassword(password);
            return userRepositories.save(user);
        }
    }
    //checking if typed userName and password matched with one in dataBase
    public User authenticate(String name,String password){
        return userRepositories.findByNameAndPassword(name,password).orElse(null);
    }

}

