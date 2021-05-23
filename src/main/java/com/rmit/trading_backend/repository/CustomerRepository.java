package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomerByNameContaining(String name);
    List<Customer> findCustomerByAddressContaining(String address);
    List<Customer> findCustomerByPhoneContaining(String phone);

}
