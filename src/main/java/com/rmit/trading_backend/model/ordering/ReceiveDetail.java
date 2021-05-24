package com.rmit.trading_backend.model.ordering;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.model.product.Product;

import javax.persistence.*;

@Entity
public class ReceiveDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    @ManyToOne
    @JsonIgnore
    private ReceivingNote receivingNote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ReceivingNote getReceivingNote() {
        return receivingNote;
    }

    public void setReceivingNote(ReceivingNote receivingNote) {
        this.receivingNote = receivingNote;
    }
}
