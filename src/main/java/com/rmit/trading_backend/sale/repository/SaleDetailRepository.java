package com.rmit.trading_backend.sale.repository;

import com.rmit.trading_backend.sale.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    List<SaleDetail> findAllBySaleInvoiceId(@Param("id") long salesInvoiceID);

    // CALCULATE TOTAL PRICE OF 1 SALE INVOICE (= totalValue all sale detail)
    @Query("SELECT SUM(sd.totalValue) FROM SaleDetail sd WHERE sd.saleInvoice.id = :saleInvoiceId")
    Long calculateTotalPriceOfASaleInvoice(@Param("saleInvoiceId") long saleInvoiceId);

}
