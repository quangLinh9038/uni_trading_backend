package com.rmit.trading_backend.inventory.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.sale.model.SaleInvoice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "deliveryNote")
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    @OneToOne
    @JoinColumn(name = "sale_invoice_id")
    private SaleInvoice saleInvoice;

    @ManyToOne
    private Staff staff;

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "deliveryNote", allowSetters = true)
    private List<DeliveryDetail> deliveryDetailList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveredDate) {
        this.deliveryDate = deliveredDate;
    }

    public SaleInvoice getSaleInvoice() {
        return saleInvoice;
    }

    public void setSaleInvoice(SaleInvoice saleInvoice) {
        this.saleInvoice = saleInvoice;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @JsonIgnore
    public List<DeliveryDetail> getDeliveryDetail() {
        return deliveryDetailList;
    }

    public void setDeliveryDetail(List<DeliveryDetail> deliveryDetails) {
        this.deliveryDetailList = deliveryDetails;
    }
}
