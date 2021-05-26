package com.rmit.trading_backend.service;

import com.rmit.trading_backend.model.sale.SaleDetail;
import com.rmit.trading_backend.model.sale.SaleInvoice;
import com.rmit.trading_backend.repository.SaleInvoiceRepository;
import com.rmit.trading_backend.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    // ADD DETAIL OF ONE INVOICE
    public boolean addInvoiceDetail(List<SaleDetail> salesInvoiceDetailList) {
        for (SaleDetail detail : salesInvoiceDetailList) {

            SaleInvoice saleInvoice = saleInvoiceRepository.findSaleInvoiceById(detail.getSaleInvoice().getId());
            Optional<SaleInvoice> checkSaleInvoice = Optional.ofNullable(saleInvoice);

            if (checkSaleInvoice.isPresent()) {

                detail.setSaleInvoice(saleInvoice);

                saleDetailRepository.saveAll(salesInvoiceDetailList);

                System.out.println("Add details successfully");
                return true;
            }
            System.out.println("Not found");
        }
        return false;
    }

}
