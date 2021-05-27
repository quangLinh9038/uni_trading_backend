package com.rmit.trading_backend.repository.actor.repository;

import com.rmit.trading_backend.model.actor.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByNameContaining(String name);

    List<Customer> findCustomerByAddressContaining(String address);

    List<Customer> findCustomerByPhoneContaining(String phone);

    Customer findCustomerById(int id);

}
