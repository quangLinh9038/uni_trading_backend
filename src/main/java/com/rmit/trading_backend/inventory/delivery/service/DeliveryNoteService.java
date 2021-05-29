package com.rmit.trading_backend.inventory.delivery.service;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;
import com.rmit.trading_backend.inventory.delivery.repository.DeliveryNoteRepository;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import com.rmit.trading_backend.sale.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DeliveryNoteService {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    // ADD DELIVERY NOTE
    public ResponseEntity<String> addDeliveryNote(DeliveryNote deliveryNote) {

        SaleInvoice si = deliveryNote.getSaleInvoice();

        if(si != null){
            Optional<SaleInvoice> saleInvoice = saleInvoiceRepository.findById(si.getId());

            if (saleInvoice.isPresent()) {

                SaleInvoice _saleInvoice = saleInvoice.get();

                deliveryNote.setSaleInvoice(_saleInvoice);

                deliveryNoteRepository.save(deliveryNote);
                System.out.println("Add delivery note successfully");
            }
            System.out.println("Sale invoice not found");

        }
        return new ResponseEntity<String>("Please input Sale Invoice", HttpStatus.NOT_FOUND);
    }
}
