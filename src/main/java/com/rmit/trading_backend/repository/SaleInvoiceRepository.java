package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.sale.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {

    SaleInvoice findSaleInvoiceById(long id);
    List<SaleInvoice> findAllByStaffName(String staffName);
    List<SaleInvoice> findAllByCustomerName(String staffName);
}
