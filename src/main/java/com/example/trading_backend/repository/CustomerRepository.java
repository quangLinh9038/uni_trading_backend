package com.example.trading_backend.repository;

import com.example.trading_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {

}
