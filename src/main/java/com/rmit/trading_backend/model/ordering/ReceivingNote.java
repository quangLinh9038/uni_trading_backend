package com.rmit.trading_backend.model.ordering;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmit.trading_backend.model.actor.Staff;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receivingNote")
public class ReceivingNote {

    //TODO
    //ReceivingNote model

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @ManyToOne
    private Staff staff;

    @OneToMany(mappedBy = "receivingNote", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ReceiveDetail> receiveDetails = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<ReceiveDetail> getReceiveDetails() {
        return receiveDetails;
    }

    public void setReceiveDetails(List<ReceiveDetail> receiveDetails) {
        this.receiveDetails = receiveDetails;
    }
}
