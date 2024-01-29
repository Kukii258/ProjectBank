package com.bank.projectbankv_01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String friend1;
    private String friend2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFriend1() {
        return friend1;
    }

    public void setFriend1(String friend1) {
        this.friend1 = friend1;
    }

    public String getFriend2() {
        return friend2;
    }

    public void setFriend2(String friend2) {
        this.friend2 = friend2;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", friend1='" + friend1 + '\'' +
                ", friend2='" + friend2 + '\'' +
                '}';
    }
}
