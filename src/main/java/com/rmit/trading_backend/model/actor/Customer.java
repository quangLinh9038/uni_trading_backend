package com.rmit.trading_backend.model.actor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.model.sale.SaleInvoice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends Person {

    @Column
    private String contact_person;

    // one customer can receive many sale invoices
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SaleInvoice> saleInvoiceList = new ArrayList<>();

    public Customer() {
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }


}

