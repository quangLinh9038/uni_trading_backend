package com.rmit.trading_backend.service;

import com.rmit.trading_backend.model.sale.DeliveryNote;
import com.rmit.trading_backend.model.sale.SaleDetail;
import com.rmit.trading_backend.model.sale.SaleInvoice;
import com.rmit.trading_backend.repository.DeliveryNoteRepository;
import com.rmit.trading_backend.repository.SaleInvoiceRepository;
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
