package com.example.trading_backend.model.actor;

import com.example.trading_backend.model.ordering.Ordering;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
public class Provider extends Person {

    @Column
    private String contact_person;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private List<Ordering> orderingList;


    public Provider() {
    }

    public String getRepresentative() {
        return contact_person;
    }

    public void setRepresentative(String representative) {
        this.contact_person = representative;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public List<Ordering> getOrderList() {
        return orderingList;
    }

    public void setOrderList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }
}
