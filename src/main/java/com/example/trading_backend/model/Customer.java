package com.example.trading_backend.model;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends Person{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private String fax;

    @ManyToOne
    private Person person;
}
