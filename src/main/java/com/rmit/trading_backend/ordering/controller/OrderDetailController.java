package com.rmit.trading_backend.ordering.controller;

import com.rmit.trading_backend.ordering.model.OrderDetail;
import com.rmit.trading_backend.ordering.model.Ordering;
import com.rmit.trading_backend.ordering.repository.OrderDetailRepository;
import com.rmit.trading_backend.ordering.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    // GET ALL ORDER DETAILS
    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetail>> getAllCategory() {
        try {
            if (orderDetailRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderDetailRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ADDING NEW ORDER_DETAILS
    @PostMapping("/orderDetails")
    public ResponseEntity<OrderDetail> addOrderDetails(@RequestBody OrderDetail orderDetail) {
        try {

            orderDetailService.createOrderDetail(orderDetail);
            return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE ORDER DETAILS
    @DeleteMapping("/orderDetails")
    public ResponseEntity<String> deleteAllOd() {
        try {
            orderDetailRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE ONE
    @DeleteMapping("/orderDetails/{id}")
    public ResponseEntity<String> deleteOrderDetailById(@PathVariable("id") int id) {
        try {

            Optional<OrderDetail> checkOd = orderDetailRepository.findById(id);

            if (checkOd.isPresent()) {
                orderDetailRepository.deleteById(id);

                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //UPDATE ORDER DETAILS
    @PutMapping("/orderDetails/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") int id, @RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> updatedOd = orderDetailRepository.findById(id);
        try {
            if (updatedOd.isPresent()) {

                OrderDetail od = updatedOd.get();

                od.setQuantity(orderDetail.getQuantity());
                od.setOrdering(orderDetail.getOrdering());
                od.setProduct(orderDetail.getProduct());
                od.setTotalPrice(orderDetail.getTotalPrice());

                orderDetailRepository.save(od);
                return new ResponseEntity<>(od, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
