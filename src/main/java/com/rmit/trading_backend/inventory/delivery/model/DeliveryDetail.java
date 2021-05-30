package com.rmit.trading_backend.inventory.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rmit.trading_backend.product.model.Product;
import com.rmit.trading_backend.sale.model.SaleDetail;

import javax.persistence.*;

@Entity
@Table(name = "delivery_detail")
public class DeliveryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int deliveryQuantity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_detail_id")
    private SaleDetail saleDetail;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "deliveryDetail", allowSetters = true)
    private DeliveryNote deliveryNote;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public long getId() {
        return id;
    }

    public int getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(int deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public SaleDetail getSaleDetail() {
        return saleDetail;
    }

    public void setSaleDetail(SaleDetail saleDetail) {
        this.saleDetail = saleDetail;
    }

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
