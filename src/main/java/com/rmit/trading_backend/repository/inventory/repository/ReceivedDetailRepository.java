package com.rmit.trading_backend.repository.inventory.repository;

import com.rmit.trading_backend.model.inventory.ReceivedDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedDetailRepository extends JpaRepository<ReceivedDetail, Long> {

}
