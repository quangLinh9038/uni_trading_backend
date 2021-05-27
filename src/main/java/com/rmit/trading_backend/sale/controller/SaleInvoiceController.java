package com.rmit.trading_backend.sale.controller;

import com.rmit.trading_backend.sale.model.SaleInvoice;
import com.rmit.trading_backend.sale.repository.SaleInvoiceRepository;
import com.rmit.trading_backend.sale.service.SaleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class SaleInvoiceController {

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    @Autowired
    private SaleInvoiceService saleInvoiceService;

    // GET ALL SALES INVOICE
    @GetMapping("/saleInvoice")
    public ResponseEntity<List<SaleInvoice>> getAllSaleInvoice() {
        try {
            // check empty list
            if (saleInvoiceRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleInvoiceRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET SALE INVOICE BY STAFF
    @GetMapping("/saleInvoiceByStaff/{name}")
    public ResponseEntity<List<SaleInvoice>> getSaleInvoiceByStaff(@PathVariable(value = "name") String staffName) {
        try {
            if (saleInvoiceRepository.findAllByStaffName(staffName).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleInvoiceRepository.findAllByStaffName(staffName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET SALE INVOICE BY CUSTOMER
    @GetMapping("/saleInvoiceByCustomer/{name}")
    public ResponseEntity<List<SaleInvoice>> getSaleInvoiceByCustomer(@PathVariable(value = "name") String customerName) {
        try {
            if (saleInvoiceRepository.findAllByCustomerName(customerName).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleInvoiceRepository.findAllByCustomerName(customerName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: filtering SALE INVOICE  by A date

    // TODO: list all SALE INVOICE  by period (startDate - endDate)

    // TODO: All sales invoice by a CUSTOMER in a period: start date and end date.

    // TODO: All sales invoice by a STAFF in a period: start date and end date.


    // POST ONE SALES INVOICE
    @PostMapping("/saleInvoice")
    public ResponseEntity<List<SaleInvoice>> addOneSaleInvoice(@RequestBody List<SaleInvoice> saleInvoices) {
        try {
            saleInvoiceService.addSaleInvoice(saleInvoices);

            if (saleInvoices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(saleInvoices, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/saleInvoice")
    public ResponseEntity<String> deleteAllSaleInvoice() {
        try {
            if (saleInvoiceRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty invoice list", HttpStatus.NO_CONTENT);
            }
            saleInvoiceRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
