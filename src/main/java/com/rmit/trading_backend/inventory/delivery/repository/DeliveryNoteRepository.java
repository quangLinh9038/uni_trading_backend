package com.rmit.trading_backend.inventory.delivery.repository;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
    List<DeliveryNote> findAllByStaffName(String staffName);
}
