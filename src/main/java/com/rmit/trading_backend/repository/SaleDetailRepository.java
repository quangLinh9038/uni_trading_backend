package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.sale.SaleDetail;
import com.rmit.trading_backend.model.sale.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    List<SaleDetail> findAllBySaleInvoiceId(@Param("id") long salesInvoiceID);
}
