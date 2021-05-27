package com.rmit.trading_backend.sale.service;

import com.rmit.trading_backend.actor.model.Customer;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.actor.repository.CustomerRepository;
import com.rmit.trading_backend.actor.repository.StaffRepository;
import com.rmit.trading_backend.sale.model.SaleDetail;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import com.rmit.trading_backend.sale.repository.SaleDetailRepository;
import com.rmit.trading_backend.sale.repository.SaleInvoiceRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleInvoiceService {

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    // POST NEW SALE INVOICE
    public void addSaleInvoice(List<SaleInvoice> saleInvoices) {

        List<SaleInvoice> saleInvoiceList = new ArrayList<>();

        for (SaleInvoice saleInvoice : saleInvoices) {

            Optional<Staff> checkStaff = staffRepository.findById(saleInvoice.getStaff().getId());
            Optional<Customer> checkCustomer = customerRepository.findById(saleInvoice.getCustomer().getId());

            if (checkStaff.isPresent() & checkCustomer.isPresent()) {

                Staff _staff = checkStaff.get();
                Customer _customer = checkCustomer.get();

                saleInvoice.setStaff(_staff);
                saleInvoice.setCustomer(_customer);
                //TODO add total price

                saleInvoiceList.add(saleInvoice);
            }
            System.out.println("Staff or Customer not found");
        }
        saleInvoiceRepository.saveAll(saleInvoiceList);
        System.out.println("Add sale invoice successfully");
    }
}
