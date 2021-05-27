package com.rmit.trading_backend.ordering.service;

import com.rmit.trading_backend.ordering.model.OrderDetail;
import com.rmit.trading_backend.ordering.model.Ordering;
import com.rmit.trading_backend.ordering.repository.OrderDetailRepository;
import com.rmit.trading_backend.ordering.repository.OrderingRepository;
import com.rmit.trading_backend.product.model.Product;
import com.rmit.trading_backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderingRepository orderingRepository;

    @Autowired
    private ProductRepository productRepository;

    // ADD NEW ORDER DETAILS
    public OrderDetail createOrderDetail(OrderDetail od) {

        Optional<Ordering> orderData = orderingRepository.findById(od.getOrdering().getId());
        System.out.println(orderData);

        Optional<Product> productData = productRepository.findById(od.getProduct().getId());
        System.out.println("productData:" + productData);

        if (orderData.isPresent() && productData.isPresent()) {

            Ordering _order = orderData.get();
            Product _product = productData.get();

            od.setOrdering(_order);
            od.setProduct(_product);
            od.setTotalPrice(_product.getPrice() * od.getQuantity());

            orderDetailRepository.save(od);
            System.out.println("Add OrderDetail successfully");

        } else {
            System.out.println("Product or Order not found");
        }
        return od;
    }
}

