package com.rmit.trading_backend.actor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.ordering.model.Ordering;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider extends Person {

    @Column
    private String contact_person;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Ordering> orderingList = new ArrayList<>();


    public Provider() {
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    @JsonIgnore
    public List<Ordering> getOrderList() {
        return orderingList;
    }

    public void setOrderList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }
}
