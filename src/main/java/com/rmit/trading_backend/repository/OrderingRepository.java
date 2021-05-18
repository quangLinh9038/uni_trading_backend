package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.ordering.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderingRepository extends JpaRepository<Ordering, Integer> {


}
