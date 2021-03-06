package com.rmit.trading_backend.inventory.receiving.controller;


import com.rmit.trading_backend.inventory.receiving.model.ReceivedDetail;
import com.rmit.trading_backend.inventory.receiving.repository.ReceivedDetailRepository;
import com.rmit.trading_backend.inventory.receiving.service.ReceivedDetailService;
import com.rmit.trading_backend.ordering.model.OrderDetail;
import com.rmit.trading_backend.ordering.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class ReceivedDetailsController {

    @Autowired
    private ReceivedDetailRepository receivedDetailRepository;

    @Autowired
    private ReceivedDetailService receivedDetailService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // GET DETAILS OF RECEIVED NOTE
    @GetMapping("/receivedDetails")
    public ResponseEntity<List<ReceivedDetail>> getAllReceiveDetails() {

        try {

            List<ReceivedDetail> receivedDetailList = receivedDetailRepository.findAll();

            if (receivedDetailList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(receivedDetailList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // ADD NEW RECEIVED DETAILS
    @PostMapping("/receivedDetail")
    public ResponseEntity<ReceivedDetail> addReceivedDetail(@RequestBody ReceivedDetail receivedDetail) {
        try {

            Optional<OrderDetail> orderDetailOpt = orderDetailRepository.findById((int) receivedDetail.getOrderDetail().getId());
            System.out.println(orderDetailOpt);

            if (orderDetailOpt.isPresent()) {

                OrderDetail od = orderDetailOpt.get();

                // get Product data from Order Details
                receivedDetail.setProduct(od.getProduct());

                receivedDetailRepository.save(receivedDetail);
                System.out.println("Success");

            }
            System.out.println("Order Details not found!");
            return new ResponseEntity<>(receivedDetail, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
