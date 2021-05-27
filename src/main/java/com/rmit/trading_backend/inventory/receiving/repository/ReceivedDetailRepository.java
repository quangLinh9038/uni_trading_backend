package com.rmit.trading_backend.inventory.receiving.repository;

import com.rmit.trading_backend.inventory.receiving.model.ReceivedDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedDetailRepository extends JpaRepository<ReceivedDetail, Long> {

}
