package com.example.trading_backend.model.sale;

import com.example.trading_backend.model.actor.Customer;
import com.example.trading_backend.model.actor.Staff;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sale")
public class SaleInvoice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate date;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Staff staff;

    @OneToMany(mappedBy = "saleInvoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleDetail> saleDetailList;

    public SaleInvoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<SaleDetail> getSaleDetailList() {
        return saleDetailList;
    }

    public void setSaleDetailList(List<SaleDetail> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }
}
