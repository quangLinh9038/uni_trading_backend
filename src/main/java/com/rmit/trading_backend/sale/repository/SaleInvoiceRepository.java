package com.rmit.trading_backend.sale.repository;

import com.rmit.trading_backend.actor.model.Customer;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

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
}
