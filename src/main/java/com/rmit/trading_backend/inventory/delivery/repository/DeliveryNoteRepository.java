package com.rmit.trading_backend.inventory.delivery.repository;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
    List<DeliveryNote> findAllByStaffName(String staffName);

    List<DeliveryNote> findAllByDeliveryDate(Date deliveryDate);

    List<DeliveryNote> findAllByDeliveryDateBetween(Date startDate, Date endDate);

}
