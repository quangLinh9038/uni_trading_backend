package com.rmit.trading_backend.inventory.delivery.controller;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryNote;
import com.rmit.trading_backend.inventory.delivery.repository.DeliveryNoteRepository;
import com.rmit.trading_backend.inventory.delivery.service.DeliveryNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class DeliveryNoteController {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    private DeliveryNoteService deliveryNoteService;

    // GET ALL DELIVERY NOTE
    @GetMapping("/deliveryNote")
    public ResponseEntity<List<DeliveryNote>> getAllDeliveryNote() {
        try {
            // check empty list
            if (deliveryNoteRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(deliveryNoteRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET SALE INVOICE BY STAFF
    @GetMapping("/deliveryNoteByStaff/{name}")
    public ResponseEntity<List<DeliveryNote>> getDeliveryNoteByStaff(@PathVariable(value = "name") String staffName) {
        try {
            if (deliveryNoteRepository.findAllByStaffName(staffName).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(deliveryNoteRepository.findAllByStaffName(staffName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL SALE INVOICE BY DATE
    @GetMapping("/deliveryNoteByDate")
    public ResponseEntity<List<DeliveryNote>> getSaleInvoiceByDate(
            @RequestParam("deliveryDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {
            return new ResponseEntity<>(deliveryNoteRepository.findAllByDeliveryDate(date), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //GET ALL DELIVERY NOTE BY A PERIOD
    @GetMapping("/deliveryNoteInPeriod")
    public ResponseEntity<List<DeliveryNote>> getDeliveryNoteInAPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            return new ResponseEntity<>(deliveryNoteRepository.findAllByDeliveryDateBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //
    @PostMapping("/deliveryNote")
    public ResponseEntity<DeliveryNote> addDeliveryNote(@RequestBody DeliveryNote deliveryNote) {

        try {
            ResponseEntity<String> responseEntity = deliveryNoteService.addDeliveryNote(deliveryNote);
            if (responseEntity.equals(new ResponseEntity(HttpStatus.NOT_FOUND))) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
//            deliveryNoteService.addDeliveryNote(deliveryNote);
            return new ResponseEntity<>(deliveryNote, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/deliveryNote")
    public ResponseEntity<String> deleteAllDeliveryNotes() {
        try {
            if (deliveryNoteRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty invoice list", HttpStatus.NO_CONTENT);
            }
            deliveryNoteRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE DETAIL
    @DeleteMapping("/deliveryNote/{id}")
    public ResponseEntity<String> deleteDeliveryNote(@PathVariable("id") long id) {
        try {
            if (deliveryNoteRepository.findById(id).isPresent()) {
                deliveryNoteRepository.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("/deliveryNote/{id}")
    public ResponseEntity<DeliveryNote> updateDeliveryNoteById(@PathVariable("id") long id, @RequestBody DeliveryNote deliveryNote) {
        Optional<DeliveryNote> updatedDeliveryNote = deliveryNoteRepository.findById(id);
        try {
            if (updatedDeliveryNote.isPresent()) {

                DeliveryNote _deliveryNote = updatedDeliveryNote.get();

                _deliveryNote.setSaleInvoice(deliveryNote.getSaleInvoice());
                _deliveryNote.setDeliveryDate(deliveryNote.getDeliveryDate());
                _deliveryNote.setStaff(deliveryNote.getStaff());

                deliveryNoteRepository.save(deliveryNote);
                return new ResponseEntity<>(deliveryNote, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
