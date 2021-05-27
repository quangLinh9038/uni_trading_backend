package com.rmit.trading_backend.inventory.delivery.service;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;
import com.rmit.trading_backend.inventory.delivery.repository.DeliveryNoteRepository;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import com.rmit.trading_backend.sale.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addDeliveryNote(DeliveryNote deliveryNote) {

        Optional<SaleInvoice> saleInvoice = saleInvoiceRepository.findById(deliveryNote.getSaleInvoice().getId());

        if (saleInvoice.isPresent()) {

            SaleInvoice _saleInvoice = saleInvoice.get();

            deliveryNote.setSaleInvoice(_saleInvoice);

            deliveryNoteRepository.save(deliveryNote);
            System.out.println("Add delivery note successfully");
        } else {
            System.out.println("Sale invoice not found");
        }
    }


}
