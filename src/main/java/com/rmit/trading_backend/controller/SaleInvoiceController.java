package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.product.Product;
import com.rmit.trading_backend.model.sale.SaleInvoice;
import com.rmit.trading_backend.repository.SaleInvoiceRepository;
import com.rmit.trading_backend.repository.StaffRepository;
import com.rmit.trading_backend.service.SaleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
