package com.rmit.trading_backend.ordering.controller;


import com.rmit.trading_backend.actor.model.Provider;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.actor.repository.ProviderRepository;
import com.rmit.trading_backend.actor.repository.StaffRepository;
import com.rmit.trading_backend.ordering.model.Ordering;
import com.rmit.trading_backend.ordering.repository.OrderingRepository;
import com.rmit.trading_backend.ordering.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class OrderingController {

    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    private OrderingRepository orderingRepository;
    @Autowired
    private OrderingService orderingService;
    @Autowired
    private StaffRepository staffRepository;

    //GET ALL ORDERS
    //SEARCH ORDER BY STAFF_NAME
    @GetMapping("/orders")
    public ResponseEntity<List<Ordering>> getAllOrders(@RequestParam(required = false) String staffName) {
        try {

            List<Ordering> orderings = new ArrayList<>();

            if (staffName == null) {
                orderingRepository.findAll().forEach(orderings::add);
            } else {
                // find order by staff name
                Optional<Staff> staff = staffRepository.findStaffByNameContains(staffName);
                if (staff.isPresent()) {
                    Staff _staff = staff.get();
                    orderingRepository.findAllByStaff(_staff).forEach(orderings::add);
                }
            }

            if (orderings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orderings, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orderByProvider")
    public ResponseEntity<List<Ordering>> getOrdersByProvider(@RequestParam(required = false) String name) {
        try {

            List<Ordering> orderings = new ArrayList<>();

            if (name == null) {
                orderingRepository.findAll().forEach(orderings::add);
            } else {
                Optional<Provider> providerData = providerRepository.findProviderByName(name);

                if (providerData.isPresent()) {
                    Provider _provider = providerData.get();
                    // find order by staff name
                    orderingRepository.findAllByProvider(_provider).forEach(orderings::add);
                }
            }

            if (orderings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orderings, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordersByDate")
    public ResponseEntity<List<Ordering>> getOrdersByDate(
            @RequestParam("orderedDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderedDate) {
        try {
//            List<Ordering> results = orderingRepository.findAllByOrderedDate(orderedDate);
//
//            if(results.isEmpty()){
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
            return new ResponseEntity<>(orderingRepository.findAllByOrderedDate(orderedDate), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //POST NEW ORDER
    @PostMapping("/orders")
    public ResponseEntity<List<Ordering>> addOrders(@RequestBody List<Ordering> orderings) {
        try {
            orderingService.addNewOrder(orderings);

            if (orderings.isEmpty()) {
                return new ResponseEntity<>(orderings, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(orderings, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE ORDERS
    @DeleteMapping("/orders")
    public ResponseEntity<String> deleteAllOrders() {
        try {
            List<Ordering> deleteOrders = orderingRepository.findAll();

            if (deleteOrders.isEmpty()) {
                return new ResponseEntity<>("Empty list", HttpStatus.NO_CONTENT);
            }
            orderingRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE AN ORDER BY ID
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {
        try {

            Optional<Ordering> checkedOrder = orderingRepository.findById(id);

            if (checkedOrder.isPresent()) {
                orderingRepository.deleteById(id);

                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //UPDATE AN ORDER
    @PutMapping("/orders/{id}")
    public ResponseEntity<Ordering> updateOrderById(@PathVariable("id") long id, @RequestBody Ordering ordering) {

        Optional<Ordering> updatedOrdering = orderingRepository.findById(id);

        if (updatedOrdering.isPresent()) {

            Ordering _ordering = updatedOrdering.get();

            _ordering.setOrderedDate(ordering.getOrderedDate());
            _ordering.setStaff(ordering.getStaff());
            _ordering.setProvider(ordering.getProvider());

            orderingRepository.save(_ordering);

            return new ResponseEntity<>(_ordering, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
