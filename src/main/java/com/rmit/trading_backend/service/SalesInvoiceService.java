package com.rmit.trading_backend.service;

import com.rmit.trading_backend.model.actor.Customer;
import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.sale.SaleInvoice;
import com.rmit.trading_backend.repository.CustomerRepository;
import com.rmit.trading_backend.repository.SaleInvoiceRepository;
import com.rmit.trading_backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SalesInvoiceService {

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    // POST NEW SALES INVOICE
    public boolean addSalesInvoice(SaleInvoice saleInvoice) {
        Staff staff = staffRepository.findStaffById(saleInvoice.getStaff().getId());
        Customer customer = customerRepository.findCustomerById(saleInvoice.getCustomer().getId());

        Optional<Staff> checkStaff = Optional.ofNullable(staff);
        Optional<Customer> checkCustomer = Optional.ofNullable(customer);

        if (checkStaff.isPresent() & checkCustomer.isPresent()) {
            saleInvoice.setStaff(staff);
            saleInvoice.setCustomer(customer);

            saleInvoiceRepository.save(saleInvoice);
            System.out.printf("Add invoice successfully");
            return true;
        }
        System.out.printf("Staff or Customer not found");
        return false;
    }
}
