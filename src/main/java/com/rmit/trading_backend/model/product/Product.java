package com.rmit.trading_backend.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.model.ordering.OrderDetail;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String model;

    @Column
    private String brand;

    @Column
    private long price;

    @Column
    private String descriptions;

    @Column
    private String company;

    @ManyToOne
    private Category category;

    @ManyToOne
    @JsonIgnore
    private OrderDetail orderDetail;


    // mapping product info to Sale Invoice
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<SaleDetail> saleDetailList;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    //
//    public List<SaleDetail> getSaleDetailList() {
//        return saleDetailList;
//    }
//
//    public void setSaleDetailList(List<SaleDetail> saleDetailList) {
//        this.saleDetailList = saleDetailList;
//    }
}
