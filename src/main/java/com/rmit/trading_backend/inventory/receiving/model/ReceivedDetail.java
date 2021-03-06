package com.rmit.trading_backend.inventory.receiving.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rmit.trading_backend.ordering.model.OrderDetail;
import com.rmit.trading_backend.product.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "received_details")
public class ReceivedDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int receivedQuantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_details_id")
    private OrderDetail orderDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "receivedDetails", allowSetters = true)
    @JsonIgnore
    private ReceivedNote receivedNote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ReceivedNote getReceivedNote() {
        return receivedNote;
    }

    public void setReceivedNote(ReceivedNote receivedNote) {
        this.receivedNote = receivedNote;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
