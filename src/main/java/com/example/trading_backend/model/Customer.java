package com.example.trading_backend.model;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends Person{

    @Column
    private String contact_person;

    public Customer() {
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }
}

