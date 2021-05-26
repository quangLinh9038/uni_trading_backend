package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.sale.SaleInvoice;
import com.rmit.trading_backend.repository.SaleInvoiceRepository;
import com.rmit.trading_backend.service.SalesInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class SaleInvoiceController {

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    @Autowired
    private SalesInvoiceService saleInvoiceService;

    // GET ALL SALES INVOICE
    @GetMapping("/salesInvoice")
    public ResponseEntity<List<SaleInvoice>> getAllSalesInvoice() {
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

    // POST ONE SALES INVOICE
    @PostMapping("/salesInvoice")
    public ResponseEntity<SaleInvoice> addOneSalesInvoice(@RequestBody SaleInvoice saleInvoice) {
        try {
            if (saleInvoiceService.addSalesInvoice(saleInvoice)) {
                return new ResponseEntity<>(saleInvoice, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/salesInvoice")
    public ResponseEntity<String> deleteAllSalesInvoice() {
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
