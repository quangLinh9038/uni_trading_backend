package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.ordering.OrderDetail;
import com.rmit.trading_backend.repository.OrderDetailRepository;
import com.rmit.trading_backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class OrderDetailController {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetail>> getAllCategory() {
        try {
            if (orderDetailRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderDetailRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ADDING NEW ORDER_DETAILS
    @PostMapping
    public ResponseEntity<List<OrderDetail>> addOrderDetails(@RequestBody List<OrderDetail> orderDetails) {
        try {
            if (orderDetailService.addOrderDetail(orderDetails)) {
                return new ResponseEntity<>(orderDetails, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
