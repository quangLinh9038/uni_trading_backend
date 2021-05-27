package com.rmit.trading_backend.model.actor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.model.inventory.ReceivedNote;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff extends Person {

    // fetching staff with 2 collection will throw MultipleBagFetchException in fetchType.Eager
    // using @LazyCollection instead of

    // one staff can make many orders
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Ordering> orderingList = new ArrayList<>();

    // one staff can make many received note
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private final List<ReceivedNote> receivedNotes = new ArrayList<>();


    // one staff can make many sale invoices
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "staff")
//    @JsonIgnore
//    private List<SaleInvoice> saleInvoiceList = new ArrayList<>();

    public Staff() {
    }

    public List<Ordering> getOrderingList() {
        return orderingList;
    }

    public void setOrderingList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }


}
