package com.rmit.trading_backend.model.ordering;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.model.actor.Staff;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receivedNote")
public class ReceivedNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date receivedDate;

    @ManyToOne
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Ordering order;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "receivedNote", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReceivedDetail> receivedDetails = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @JsonIgnore
    public List<ReceivedDetail> getReceiveDetails() {
        return receivedDetails;
    }

    public void setReceiveDetails(List<ReceivedDetail> receivedDetails) {
        this.receivedDetails = receivedDetails;
    }

    public Ordering getOrder() {
        return order;
    }

    public void setOrder(Ordering order) {
        this.order = order;
    }
}
