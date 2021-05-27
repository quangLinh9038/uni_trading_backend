package com.rmit.trading_backend.inventory.receiving.service;

import com.rmit.trading_backend.inventory.receiving.model.ReceivedDetail;
import com.rmit.trading_backend.inventory.receiving.repository.ReceivedDetailRepository;
import com.rmit.trading_backend.ordering.model.OrderDetail;
import com.rmit.trading_backend.ordering.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ReceivedDetailService {

    @Autowired
    private ReceivedDetailRepository receivedDetailRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // CREATE A NEW DETAILS OF RECEIVED NOTE
    public void createReceivedDetail(ReceivedDetail receivedDetail) {

        // check valid order details
        Optional<OrderDetail> orderDetailOpt = orderDetailRepository.findById((int) receivedDetail.getOrderDetail().getId());
        System.out.println(orderDetailOpt);

        if (orderDetailOpt.isPresent()) {

            OrderDetail od = orderDetailOpt.get();

            // get Product data from Order Details
            receivedDetail.setProduct(od.getProduct());

            receivedDetailRepository.save(receivedDetail);
            System.out.println("Success");
        } else {
            System.out.println("Order Details not found!");
        }
    }

}
