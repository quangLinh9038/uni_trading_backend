package com.rmit.trading_backend.inventory.receiving.repository;

import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReceivedNoteRepository extends JpaRepository<ReceivedNote, Long> {


    List<ReceivedNote> findAllByReceivedDateBetween(Date startDate, Date endDate);

    List<ReceivedNote> findAllByReceivedDate(Date receivedDate);
}
