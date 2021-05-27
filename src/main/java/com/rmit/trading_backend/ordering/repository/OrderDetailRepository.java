package com.rmit.trading_backend.ordering.repository;

import com.rmit.trading_backend.ordering.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
