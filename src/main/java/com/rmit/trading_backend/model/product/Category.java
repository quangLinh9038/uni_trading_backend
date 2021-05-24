package com.rmit.trading_backend.model.product;
import javax.persistence.*;

@Embeddable
public class Category {

    private String name;

    public Category(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
