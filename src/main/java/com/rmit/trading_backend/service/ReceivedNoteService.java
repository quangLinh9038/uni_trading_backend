package com.rmit.trading_backend.service;

import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.model.inventory.ReceivedNote;
import com.rmit.trading_backend.repository.inventory.repository.ReceivedNoteRepository;
import com.rmit.trading_backend.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ReceivedNoteService {


    @Autowired
    private ReceivedNoteRepository receivedNoteRepository;

    @Autowired
    private OrderingRepository orderingRepository;

    // ADD NEW RECEIVED NOTES
    public void createReceivedNotes(ReceivedNote receivedNote) {

        Optional<Ordering> orderData = orderingRepository.findById(receivedNote.getOrder().getId());
        System.out.println(orderData);

        if (orderData.isPresent()) {

            Ordering _order = orderData.get();

            receivedNote.setOrder(_order);

            receivedNoteRepository.save(receivedNote);
            System.out.println("ReceivedNote add success");
        } else {
            System.out.println("Order not found!");
        }
    }
}
