package com.rmit.trading_backend.service;

import com.rmit.trading_backend.model.ordering.OrderDetail;
import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.model.product.Product;
import com.rmit.trading_backend.repository.OrderDetailRepository;
import com.rmit.trading_backend.repository.product.repository.ProductRepository;
import com.rmit.trading_backend.repository.OrderingRepository;
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
    public void createOrderDetail(OrderDetail od) {

        Optional<Ordering> orderData = orderingRepository.findById(od.getOrdering().getId());

        Optional<Product> productData = productRepository.findById((int) od.getProduct().getId());
        System.out.println("productData:" + productData);

        if (orderData.isPresent() && productData.isPresent()) {

            Ordering _order = orderData.get();
            Product _product = productData.get();

            System.out.println("_product" + _product);
            od.setOrdering(_order);
            od.setProduct(_product);
            od.setTotalPrice(_product.getPrice() * od.getQuantity());

            orderDetailRepository.save(od);
            System.out.println("Add OrderDetail successfully");

        } else {
            System.out.println("Product or Order not found");
        }
    }
}

