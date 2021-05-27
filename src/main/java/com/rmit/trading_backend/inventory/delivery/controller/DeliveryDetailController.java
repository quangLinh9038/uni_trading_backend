package com.rmit.trading_backend.inventory.delivery.controller;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryDetail;
import com.rmit.trading_backend.inventory.delivery.repository.DeliveryDetailRepository;
import com.rmit.trading_backend.inventory.delivery.service.DeliveryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class DeliveryDetailController {

    @Autowired
    private DeliveryDetailRepository deliveryDetailRepository;

    @Autowired
    private DeliveryDetailService deliveryDetailService;

    // GET ALL DELIVERY DETAILS
    @GetMapping("/deliveryDetails")
    public ResponseEntity<List<DeliveryDetail>> getAllDeliveryDetail() {
        try {
            if (deliveryDetailRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(deliveryDetailRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET DETAILS BY DELIVERY NOTE ID
    @GetMapping("/deliveryNoteDetails/{id}")
    public ResponseEntity<List<DeliveryDetail>> getDetailsByDeliveryNoteID(@PathVariable("id") long deliveryNoteId) {
        try {
            if (deliveryDetailRepository.findAllByDeliveryNoteId(deliveryNoteId).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(deliveryDetailRepository.findAllByDeliveryNoteId(deliveryNoteId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST DETAILS TO A SALE INVOICE
    @PostMapping("/deliveryDetails")
    public ResponseEntity<DeliveryDetail> addDetails(@RequestBody DeliveryDetail deliveryDetail) {

        try {
            deliveryDetailService.addDeliveryDetail(deliveryDetail);
            return new ResponseEntity<>(deliveryDetail, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/deliveryDetails")
    public ResponseEntity<String> deleteAllDeliveryDetails() {
        try {
            if (deliveryDetailRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty invoice list", HttpStatus.NO_CONTENT);
            }
            deliveryDetailRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE DETAIL
    @DeleteMapping("/deliveryDetails/{id}")
    public ResponseEntity<String> deleteDetail(@PathVariable("id") long id) {
        try {
            if (deliveryDetailRepository.findById(id).isPresent()) {
                deliveryDetailRepository.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("/deliveryDetails/{id}")
    public ResponseEntity<DeliveryDetail> updateDeliveryDetailById(@PathVariable("id") long id, @RequestBody DeliveryDetail deliveryDetail) {
        Optional<DeliveryDetail> checkDeliveryDetail = deliveryDetailRepository.findById(id);
        try {
            if (checkDeliveryDetail.isPresent()) {

                DeliveryDetail _deliveryDetail = checkDeliveryDetail.get();

                _deliveryDetail.setProduct(deliveryDetail.getProduct());
                _deliveryDetail.setDeliveryQuantity(deliveryDetail.getDeliveryQuantity());
                _deliveryDetail.setDeliveryNote(deliveryDetail.getDeliveryNote());

                deliveryDetailRepository.save(deliveryDetail);
                return new ResponseEntity<>(deliveryDetail, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
