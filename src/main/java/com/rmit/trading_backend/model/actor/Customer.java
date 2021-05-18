package com.rmit.trading_backend.model.actor;

import com.rmit.trading_backend.model.sale.SaleInvoice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends Person {

    @Column
    private String contact_person;

    // one customer can receive many sale invoices
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<SaleInvoice> saleInvoiceList;

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

    public void setSaleInvoiceList(List<SaleInvoice> saleInvoiceList) {
        this.saleInvoiceList = saleInvoiceList;
    }
}

