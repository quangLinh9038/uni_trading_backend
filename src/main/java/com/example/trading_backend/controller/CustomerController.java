package com.example.trading_backend.controller;

import com.example.trading_backend.model.Customer;
import com.example.trading_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = {"/customers","/all"}, method = RequestMethod.GET)
    public List<Customer> getAllCus(){
        return customerService.getAll();
    }

    @RequestMapping(path = "/customers", method = RequestMethod.POST)
    public int addCustomer(Customer customer){
        return customerService.addCustomer(customer);
    }
}
