package com.example.trading_backend.repository;

import com.example.trading_backend.model.actor.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    Optional<Customer> findCustomerByNameContaining(String name);

}
