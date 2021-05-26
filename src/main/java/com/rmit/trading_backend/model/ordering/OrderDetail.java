package com.rmit.trading_backend.model.ordering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int quantity;

    @Column
    private long totalPrice;

    @ManyToOne
    private Ordering ordering;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JsonIgnore
    private ReceivedDetail receivedDetail;

    public OrderDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ReceivedDetail getReceivedDetail() {
        return receivedDetail;
    }

    public void setReceivedDetail(ReceivedDetail receivedDetail) {
        this.receivedDetail = receivedDetail;
    }
}
