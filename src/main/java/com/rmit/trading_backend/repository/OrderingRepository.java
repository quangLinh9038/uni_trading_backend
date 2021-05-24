package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.ordering.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    List<Ordering> findAllByStaff (Staff staff);
}
