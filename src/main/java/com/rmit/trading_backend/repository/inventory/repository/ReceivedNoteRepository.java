package com.rmit.trading_backend.repository.inventory.repository;

import com.rmit.trading_backend.model.inventory.ReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReceivedNoteRepository extends JpaRepository<ReceivedNote, Long> {


    List<ReceivedNote> findAllByReceivedDateBetween(Date startDate, Date endDate);

    List<ReceivedNote> findAllByReceivedDate (Date receivedDate);
}
