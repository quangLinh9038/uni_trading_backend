package com.rmit.trading_backend.repository.inventory.repository;

import com.rmit.trading_backend.model.ordering.ReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedNoteRepository extends JpaRepository<ReceivedNote, Long> {

}
