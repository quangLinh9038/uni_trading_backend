package com.rmit.trading_backend.sale.repository;

import com.rmit.trading_backend.sale.model.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {

    List<SaleInvoice> findAllByStaffName(String staffName);

    List<SaleInvoice> findAllByCustomerName(String staffName);

    List<SaleInvoice> findAllBySoldDate(Date date);

    List<SaleInvoice> findAllBySoldDateBetween(Date startDate, Date endDate);

    List<SaleInvoice> findAllByStaffNameAndSoldDateBetween(String staffName, Date startDate, Date endDate);

    List<SaleInvoice> findAllByCustomerNameAndSoldDateBetween(String customerName, Date date, Date date2);

    // REVENUE OF ALL TIME
    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si")
    Long calculateRevenue();

    // ALL REVENUE IN A PERIOD
    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si WHERE si.soldDate BETWEEN :startDate AND :endDate")
    Long calculateRevenueBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // REVENUE BY CUSTOMER
    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si WHERE si.customer.name = :customerName AND si.soldDate BETWEEN :startDate AND :endDate")
    Long calculateRevenueByCustomerBetween(@Param("customerName") String customerName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // REVENUE BY SALE STAFF
    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si WHERE si.staff.name = :staffName AND si.soldDate BETWEEN :startDate AND :endDate")
    Long calculateRevenueByStaffBetween(@Param("staffName") String staffName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
