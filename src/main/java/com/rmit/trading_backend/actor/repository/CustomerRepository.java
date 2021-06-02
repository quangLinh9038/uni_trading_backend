package com.rmit.trading_backend.actor.repository;

import com.rmit.trading_backend.actor.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByNameContaining(String name);

    List<Customer> findCustomerByAddressContaining(String address);

    List<Customer> findCustomerByPhoneContaining(String phone);

    Optional<Customer> findCustomerByNameContains(String customerName);

    Customer findCustomerById(int id);

}
