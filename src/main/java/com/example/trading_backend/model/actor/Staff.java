package com.example.trading_backend.model.actor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STAFF")
public class Staff extends Person {

    private String company;

    public Staff(){
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
