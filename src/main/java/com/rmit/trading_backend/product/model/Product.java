package com.rmit.trading_backend.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rmit.trading_backend.inventory.delivery.model.DeliveryDetail;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedDetail;
import com.rmit.trading_backend.ordering.model.OrderDetail;
import com.rmit.trading_backend.sale.model.SaleDetail;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    // mapping to orderDetail
    @OneToOne
    @JsonIgnore
    private OrderDetail orderDetail;

    @OneToOne
    @JsonIgnore
    private ReceivedDetail receivedDetail;

    @OneToOne
    @JsonIgnore
    private SaleDetail saleDetail;

    @OneToOne
    @JsonIgnore
    private DeliveryDetail deliveryDetail;


    public Product() {
    }


    // standard getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public SaleDetail getSaleDetail() {
        return saleDetail;
    }

    public void setSaleDetail(SaleDetail saleDetail) {
        this.saleDetail = saleDetail;
    }

    public DeliveryDetail getDeliveryDetail() {
        return deliveryDetail;
    }

    public void setDeliveryDetail(DeliveryDetail deliveryDetail) {
        this.deliveryDetail = deliveryDetail;
    }

    public ReceivedDetail getReceivedDetail() {
        return receivedDetail;
    }

    public void setReceivedDetail(ReceivedDetail receivedDetail) {
        this.receivedDetail = receivedDetail;
    }

//    public Inventory getInventory() {
//        return inventory;
//    }
//
//    public void setInventory(Inventory inventory) {
//        this.inventory = inventory;
//    }
}

