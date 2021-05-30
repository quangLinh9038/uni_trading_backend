package com.rmit.trading_backend.inventory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import com.rmit.trading_backend.product.model.Product;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "Product Name")
    private String product;

    @Column(name = "Received")
    private long receivedQuantity;

    @Column(name = "Delivered")
    private long deliveredQuantity;


    @Column(name = "Balance")
    private long balance;

//    @OneToMany
//    private Product product;


    public Inventory(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public long getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(long receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public long getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(long deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
