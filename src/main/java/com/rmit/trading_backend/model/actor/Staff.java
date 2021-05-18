package com.rmit.trading_backend.model.actor;

import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.model.sale.SaleInvoice;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff extends Person {

    private String company;

    // one staff can make many orders
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Ordering> orderingList;

    // one staff can make many sale invoices
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<SaleInvoice> saleInvoiceList;

    public Staff() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Ordering> getOrderList() {
        return orderingList;
    }

    public void setOrderList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }

    public List<Ordering> getOrderingList() {
        return orderingList;
    }

    public void setOrderingList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }

    public List<SaleInvoice> getSaleInvoiceList() {
        return saleInvoiceList;
    }

    public void setSaleInvoiceList(List<SaleInvoice> saleInvoiceList) {
        this.saleInvoiceList = saleInvoiceList;
    }
}
