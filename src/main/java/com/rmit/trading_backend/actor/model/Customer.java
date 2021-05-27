package com.rmit.trading_backend.actor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.sale.model.SaleInvoice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends Person {

    // one customer can receive many sale invoices
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private final List<SaleInvoice> saleInvoiceList = new ArrayList<>();

    // TODO: mapping to DELIVERY NOTE

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

    public List<SaleInvoice> getSaleInvoiceList() {
        return saleInvoiceList;
    }



}

