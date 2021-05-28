package com.rmit.trading_backend.sale.controller;

import com.rmit.trading_backend.product.model.Product;
import com.rmit.trading_backend.sale.model.SaleDetail;
import com.rmit.trading_backend.sale.repository.SaleDetailRepository;
import com.rmit.trading_backend.sale.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class SaleDetailController {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private SaleDetailService saleDetailService;

    // GET ALL SALE DETAILS
    @GetMapping("/saleDetails")
    public ResponseEntity<List<SaleDetail>> getAllSalesInvoiceDetail() {
        try {
            // check empty list
            if (saleDetailRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleDetailRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET DETAILS BY SALES INVOICE ID
    @GetMapping("/saleInvoiceDetails/{id}")
    public ResponseEntity<List<SaleDetail>> getDetailsBySaleInvoiceID(@PathVariable("id") long saleInvoiceId) {
        try {
            if (saleDetailRepository.findAllBySaleInvoiceId(saleInvoiceId).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(saleDetailRepository.findAllBySaleInvoiceId(saleInvoiceId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST DETAILS TO A SALE INVOICE
    @PostMapping("/saleDetails")
    public ResponseEntity<SaleDetail> addDetails(@RequestBody SaleDetail saleDetail) {

        try {
            saleDetailService.addSaleDetail(saleDetail);
            return new ResponseEntity<>(saleDetail, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/saleDetails")
    public ResponseEntity<String> deleteAllSaleDetails() {
        try {
            if (saleDetailRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty invoice list", HttpStatus.NO_CONTENT);
            }
            saleDetailRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE DETAIL
    @DeleteMapping("/saleDetails/{id}")
    public ResponseEntity<String> deleteDetail(@PathVariable("id") long id) {
        try {
            if (saleDetailRepository.findById(id).isPresent()) {
                saleDetailRepository.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("/saleDetails/{id}")
    public ResponseEntity<SaleDetail> updateSaleDetailById(@PathVariable("id") long id, @RequestBody SaleDetail saleDetail) {
        Optional<SaleDetail> updatedSaleDetail = saleDetailRepository.findById(id);

        try {
            if (updatedSaleDetail.isPresent()) {

                SaleDetail _saleDetail = updatedSaleDetail.get();

                _saleDetail.setProduct(saleDetail.getProduct());
                _saleDetail.setPrice(saleDetail.getPrice());
                _saleDetail.setQuantity(saleDetail.getQuantity());
                _saleDetail.setTotalValue(saleDetail.getPrice() * saleDetail.getQuantity());

                saleDetailRepository.save(saleDetail);
                return new ResponseEntity<>(saleDetail, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // TEST
    @GetMapping("/test1")
    public ResponseEntity<Long> test1(
            @RequestParam(required = false) long saleInvoiceId
    ) {
        try{
            if (saleDetailRepository.calculateTotalPriceOfASaleInvoice(saleInvoiceId) != null) {
                System.out.println(saleDetailRepository.calculateTotalPriceOfASaleInvoice(saleInvoiceId));
                return new ResponseEntity<>(saleDetailRepository.calculateTotalPriceOfASaleInvoice(saleInvoiceId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
