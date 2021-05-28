package com.rmit.trading_backend.sale.controller;

import com.rmit.trading_backend.actor.model.Customer;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.actor.repository.CustomerRepository;
import com.rmit.trading_backend.actor.repository.StaffRepository;
import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;
import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import com.rmit.trading_backend.ordering.model.Ordering;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import com.rmit.trading_backend.sale.repository.SaleDetailRepository;
import com.rmit.trading_backend.sale.repository.SaleInvoiceRepository;
import com.rmit.trading_backend.sale.service.SaleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    // GET ALL SALES INVOICE BY ID
    @GetMapping("/saleInvoice/{id}")
    public ResponseEntity<Optional<SaleInvoice>> getAllSaleInvoiceById(@PathVariable(value = "id") long id) {
        try {
            // check empty list
            if (saleInvoiceRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleInvoiceRepository.findById(id), HttpStatus.OK);
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

    // GET ALL SALE INVOICE BY DATE
    @GetMapping("/saleInvoiceByDate")
    public ResponseEntity<List<SaleInvoice>> getSaleInvoiceByDate(
            @RequestParam("saleInvoiceDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {

            return new ResponseEntity<>(saleInvoiceRepository.findAllBySoldDate(date), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //GET ALL SALE INVOICE BY A PERIOD
    @GetMapping("/saleInvoiceInPeriod")
    public ResponseEntity<List<SaleInvoice>> getSaleInvoiceInAPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        try{
            return new ResponseEntity<>(saleInvoiceRepository.findAllBySoldDateBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET SALE INVOICE BY CUSTOMER
    @GetMapping("/saleInvoiceByCustomerInPeriod")
    public ResponseEntity<List<SaleInvoice>> getAllSaleInvoiceByCustomerInAPeriod(
            @RequestParam(required = false) String customerName,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try{
            return new ResponseEntity<>(saleInvoiceRepository.findAllByCustomerNameAndSoldDateBetween(customerName, startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET SALE INVOICE BY STAFF
    @GetMapping("/saleInvoiceByStaffInPeriod")
    public ResponseEntity<List<SaleInvoice>> getAllSaleInvoiceByStaffInAPeriod(
            @RequestParam(required = false) String staffName,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try{
            return new ResponseEntity<>(saleInvoiceRepository.findAllByStaffNameAndSoldDateBetween(staffName, startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET REVENUE
    @GetMapping("/revenue")
    public ResponseEntity<Long> calculateFinalRevenue() {
        try{
            return new ResponseEntity<>(saleInvoiceRepository.calculateRevenue(), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET REVENUE IN A PERIOD
    @GetMapping("/revenueInPeriod")
    public ResponseEntity<Long> calculateRevenueInPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        try{
            return new ResponseEntity<>(saleInvoiceRepository.calculateRevenueBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET REVENUE BY A CUSTOMER IN A PERIOD
    @GetMapping("/revenueByCustomerInPeriod")
    public ResponseEntity<Long> calculateRevenueByCustomerInPeriod(
            @RequestParam(required = false) String customerName,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        try{
            return new ResponseEntity<>(saleInvoiceRepository.calculateRevenueByCustomerBetween(customerName, startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET REVENUE BY A STAFF IN A PERIOD
    @GetMapping("/revenueBySaleStaffInPeriod")
    public ResponseEntity<Long> calculateRevenueBySaleStaffInPeriod(
            @RequestParam(required = false) String staffName,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        try{
            return new ResponseEntity<>(saleInvoiceRepository.calculateRevenueByStaffBetween(staffName, startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
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

    // UPDATE
    @PutMapping("/saleInvoice/{id}")
    public ResponseEntity<SaleInvoice> updateSaleInvoiceById(@PathVariable("id") long id, @RequestBody SaleInvoice saleInvoice) {
        Optional<SaleInvoice> updatedSaleInvoice = saleInvoiceRepository.findById(id);
        Long sum = saleDetailRepository.calculateTotalPriceOfASaleInvoice(id);
        System.out.println(saleInvoice.getId());
        System.out.println(sum);

        try {
            if (updatedSaleInvoice.isPresent()) {

                SaleInvoice _saleInvoice = updatedSaleInvoice.get();

                _saleInvoice.setSoldDate(saleInvoice.getSoldDate());
                _saleInvoice.setStaff(saleInvoice.getStaff());
                _saleInvoice.setCustomer(saleInvoice.getCustomer());
                _saleInvoice.setTotalPrice(sum);

                saleInvoiceRepository.save(saleInvoice);
                return new ResponseEntity<>(saleInvoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE TOTAL PRICE OF ONE SALE INVOICE
    @PutMapping("/totalPriceSaleInvoice/{id}")
    public ResponseEntity<Optional<SaleInvoice>> updateTotalPriceOfSaleInvoiceById(@PathVariable("id") long id) {
        Optional<SaleInvoice> updatedSaleInvoice = saleInvoiceRepository.findById(id);
        Long sum = saleDetailRepository.calculateTotalPriceOfASaleInvoice(id);
        System.out.println(id);
        System.out.println(sum);

        try {
            if (updatedSaleInvoice.isPresent()) {

                SaleInvoice _saleInvoice = updatedSaleInvoice.get();

                _saleInvoice.setTotalPrice(sum);

                saleInvoiceRepository.save(_saleInvoice);
                return new ResponseEntity<>(saleInvoiceRepository.findById(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
