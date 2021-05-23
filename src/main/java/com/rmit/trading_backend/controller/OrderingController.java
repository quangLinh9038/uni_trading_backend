package com.rmit.trading_backend.controller;


import com.rmit.trading_backend.model.ordering.OrderDetail;
import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.repository.OrderingRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Order
// CRUD functions

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class OrderingController {
    @Autowired
    private OrderingRepository orderingRepository;

    //GET ALL ORDERS
    @GetMapping("/orders")
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
    @PostMapping("/orders")
    public ResponseEntity<List<Ordering>> addCategory(@RequestBody List<Ordering> orderings) {
        try {
            orderingRepository.saveAll(orderings);

            return new ResponseEntity<>(orderings, HttpStatus.CREATED);

//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
