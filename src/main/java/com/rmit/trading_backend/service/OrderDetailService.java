package com.rmit.trading_backend.service;

import com.rmit.trading_backend.model.ordering.OrderDetail;
import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.repository.OrderDetailRepository;
import com.rmit.trading_backend.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderingRepository orderingRepository;

    // ADD NEW ORDER DETAILS
    public boolean addOrderDetail(List<OrderDetail> orderDetailList) {
        for (OrderDetail od : orderDetailList) {

            Optional<Ordering> order = orderingRepository.findById(od.getOrdering().getId());

            if (order.isPresent()) {
                orderDetailRepository.saveAll(orderDetailList);

                System.out.println("Add OrderDetail successfully");
                return true;
            }
            System.out.println("Not found requested Order");
        }
        return false;
    }
}
