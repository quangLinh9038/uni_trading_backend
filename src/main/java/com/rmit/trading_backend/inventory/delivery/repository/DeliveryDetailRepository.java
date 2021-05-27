package com.rmit.trading_backend.inventory.delivery.repository;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetail, Long> {

    List<DeliveryDetail> findAllByDeliveryNoteId(@Param("id") long deliveryNoteID);

}
