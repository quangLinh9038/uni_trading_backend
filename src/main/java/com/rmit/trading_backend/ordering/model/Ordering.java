package com.rmit.trading_backend.ordering.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rmit.trading_backend.actor.model.Provider;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordering")
public class Ordering {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderedDate;

    // mapping to Provider
    @ManyToOne
    private Provider provider;

    // mapping to Staff
    @ManyToOne
    private Staff staff;

    //    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "ordering", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "ordering", allowSetters = true)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    // mapping to ReceivedNote
    @OneToOne
    @JsonIgnore
    private ReceivedNote receivedNote;

    public Ordering() {
    }

    public Ordering(Date orderedDate, Staff staff, Provider provider) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public ReceivedNote getReceivedNote() {
        return receivedNote;
    }

    public void setReceivedNote(ReceivedNote receivedNote) {
        this.receivedNote = receivedNote;
    }
}
