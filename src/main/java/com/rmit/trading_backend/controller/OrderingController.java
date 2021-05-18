package com.rmit.trading_backend.controller;


import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// TODO: Order
// CRUD functions
public class OrderingController {
    @Autowired
    private OrderingRepository orderingRepository;

    //GET ALL ORDERS
    @GetMapping("/ordering")
    public ResponseEntity<List<Ordering>> getAllCategory() {
        try {
            if (orderingRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderingRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST NEW ORDER
    @PostMapping("/ordering")
    public ResponseEntity<List<Ordering>> addCategory(@RequestBody List<Ordering> categories) {
        try {
            List<Ordering> categoryList = orderingRepository.saveAll(categories);
            return new ResponseEntity<>(categoryList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
