package com.rmit.trading_backend.sale.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.actor.model.Customer;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale_invoice")
public class SaleInvoice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @Column(name = "total_price")
    private long totalPrice;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Staff staff;

    //TODO mapping as Order
    @OneToMany(mappedBy = "saleInvoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SaleDetail> saleDetailList = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    private DeliveryNote deliveryNote;

    public SaleInvoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryNote getDeliveryNode() {
        return deliveryNote;
    }

    public void setDeliveryNode(DeliveryNote deliveryNode) {
        this.deliveryNote = deliveryNode;
    }


}
