package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.ordering.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
