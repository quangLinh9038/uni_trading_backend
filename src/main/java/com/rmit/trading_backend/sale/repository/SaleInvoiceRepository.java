package com.rmit.trading_backend.sale.repository;

import com.rmit.trading_backend.actor.model.Customer;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {

    SaleInvoice findSaleInvoiceById(long id);

    List<SaleInvoice> findAllByStaff(Staff staff);

    List<SaleInvoice> findAllByCustomer(Customer customer);

    List<SaleInvoice> findAllByStaffName(String staffName);

    List<SaleInvoice> findAllByCustomerName(String staffName);

    List<SaleInvoice> findAllBySoldDate(Date date);

    List<SaleInvoice> findAllBySoldDateBetween(Date startDate, Date endDate);

    List<SaleInvoice> findAllByStaffNameAndSoldDateBetween(String staffName, Date startDate, Date endDate);

    List<SaleInvoice> findAllByCustomerNameAndSoldDateBetween(String customerName, Date date, Date date2);

    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si")
    Long calculateRevenue();

    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si WHERE si.soldDate BETWEEN :startDate AND :endDate")
    Long calculateRevenueBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si WHERE si.customer.name = :customerName AND si.soldDate BETWEEN :startDate AND :endDate")
    Long calculateRevenueByCustomerBetween(@Param("customerName") String customerName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(si.totalPrice) FROM SaleInvoice si WHERE si.staff.name = :staffName AND si.soldDate BETWEEN :startDate AND :endDate")
    Long calculateRevenueByStaffBetween(@Param("staffName") String staffName, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Modifying
    @Query("UPDATE SaleInvoice si SET si.totalPrice = :totalPrice WHERE si.id = :saleInvoiceId")
    Optional<SaleInvoice> updateTotalPriceSaleInvoice(@Param("totalPrice") long totalPrice, @Param("saleInvoiceId") long saleInvoiceId);
}