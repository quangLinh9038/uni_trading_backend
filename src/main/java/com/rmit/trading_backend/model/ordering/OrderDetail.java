package com.rmit.trading_backend.model.ordering;

import com.rmit.trading_backend.model.product.Product;

import javax.persistence.*;

@Entity
public class OrderDetail {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int quantity;

    @Column
    private long price;

    @ManyToOne
    private Ordering ordering;

    @ManyToOne
    private Product product;


    public OrderDetail() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ordering getOrder() {
        return ordering;
    }

    public void setOrder(Ordering ordering) {
        this.ordering = ordering;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuanity() {
        return quantity;
    }

    public void setQuanity(int quanity) {
        this.quantity = quanity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
