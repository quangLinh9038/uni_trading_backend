package com.example.trading_backend.controller;

import com.example.trading_backend.model.actor.Customer;
import com.example.trading_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CustomerController {

//    @Autowired
//    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    // get all customers
    @GetMapping(value = {"/", "customers"})
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            // check empty list
            if (customerRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ONE BY NAME
    @GetMapping("/customerByName/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("name") String name) {
        try {
            Optional<Customer> customer = customerRepository.findCustomerByNameContaining(name);
            return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            System.out.println("error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST NEW CUSTOMER
    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody List<Customer> customers) {
        try {
            // define new Customer
            List<Customer> customerList = customerRepository.saveAll(customers);
            return new ResponseEntity<>(customerList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
        try {
            if (customerRepository.findById(id).isPresent()) {
                customerRepository.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteALlCustomer() {
        try {
            if (customerRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty customer list", HttpStatus.NO_CONTENT);
            }
            customerRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("customers/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") int id, @RequestBody Customer customer) {
        Optional<Customer> updatedCustomer = customerRepository.findById(id);

        if (updatedCustomer.isPresent()) {

            Customer _customer = updatedCustomer.get();

            _customer.setName(customer.getName());
            _customer.setPhone(customer.getPhone());
            _customer.setAddress(customer.getAddress());
            _customer.setEmail(customer.getEmail());
            _customer.setFax(customer.getFax());
            _customer.setContact_person(customer.getContact_person());

            customerRepository.save(_customer);
            return new ResponseEntity<>(_customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


