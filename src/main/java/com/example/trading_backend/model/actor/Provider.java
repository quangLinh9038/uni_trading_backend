package com.example.trading_backend.model.actor;

import com.example.trading_backend.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROVIDER")
public class Provider extends Person {

    @Column
    private String representative;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    public Provider(){
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
