//package com.example.trading_backend.service;
//
//
//import com.example.trading_backend.model.actor.Customer;
//import com.example.trading_backend.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//public class CustomerService {
//
////    @Autowired
////    private CustomerRepository customerRepository;
//
//    public List<Customer> getALlCustomers(){
//        return (List<Customer>) customerRepository.findAll();
//    }
//
//    public Optional<Customer> findById(int id){
//        return customerRepository.findById(id);
//    }
//
//    public Optional<Customer> findByName(String name){
//        return customerRepository.findCustomerByNameContaining(name);
//    }
//
//    public void deleteById(int id ){
//        customerRepository.deleteById(id);
//    }
//
//}
