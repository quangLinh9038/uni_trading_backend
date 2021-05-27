package com.rmit.trading_backend.inventory.receiving.controller;

import com.rmit.trading_backend.inventory.receiving.model.ReceivedNote;
import com.rmit.trading_backend.inventory.receiving.repository.ReceivedNoteRepository;
import com.rmit.trading_backend.inventory.receiving.service.ReceivedNoteService;
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
public class ReceivedNoteController {

    @Autowired
    private ReceivedNoteRepository receivedNoteRepository;

    @Autowired
    private ReceivedNoteService receivedNoteServices;

    //GET ALL RECEIVED NOTES
    @GetMapping("/receivedNotes")
    public ResponseEntity<List<ReceivedNote>> getAllReceivedNotes() {
        try {

            List<ReceivedNote> receivedNotes = receivedNoteRepository.findAll();

            if (receivedNotes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(receivedNotes, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL RECEIVED NOTES
    @GetMapping("/receivedNotesByDate")
    public ResponseEntity<List<ReceivedNote>> getReceivedNotesByDate(
            @RequestParam("receivedDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date receivedDate) {
        try {

            return new ResponseEntity<>(receivedNoteRepository.findAllByReceivedDate(receivedDate), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //TODO: List all received note by a period:
    @GetMapping("/receivedNoteInPeriod")
    public ResponseEntity<List<ReceivedNote>> getReceivedNotesInAPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ){
        try{
            return new ResponseEntity<>(receivedNoteRepository.findAllByReceivedDateBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // CREATE A NEW RECEIVED NOTES
    // ADD ONE
    @PostMapping("/receivedNotes")
    public ResponseEntity<ReceivedNote> createReceivedNote(@RequestBody ReceivedNote receivedNote) {
        try {
            receivedNoteServices.createReceivedNotes(receivedNote);

            return new ResponseEntity<>(receivedNote, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE RECEIVED NOTE
    @DeleteMapping("/receivedNotes")
    public ResponseEntity<String> deleteAllReceivedNotes() {
        try {
            List<ReceivedNote> deleteReceivedNotes = receivedNoteRepository.findAll();

            if (deleteReceivedNotes.isEmpty()) {
                return new ResponseEntity<>("Empty list", HttpStatus.NO_CONTENT);
            }
            receivedNoteRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // DELETE NOTE BY ID
    @DeleteMapping("/receivedNotes/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {
            Optional<ReceivedNote> receivedNote = receivedNoteRepository.findById(id);

            if (receivedNote.isPresent()) {
                receivedNoteRepository.deleteById(id);

                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/receivedNotes/{id}")
    public ResponseEntity<ReceivedNote> updateReceivedNoteById(@PathVariable("id") long id, @RequestBody ReceivedNote receivedNote) {

        try {
            Optional<ReceivedNote> updatedReceivedNote = receivedNoteRepository.findById(id);

            if (updatedReceivedNote.isPresent()) {

                ReceivedNote newReceivedNote = updatedReceivedNote.get();

                newReceivedNote.setReceivedDate(receivedNote.getReceivedDate());
                newReceivedNote.setStaff(receivedNote.getStaff());
                newReceivedNote.setOrder(receivedNote.getOrder());

                receivedNoteRepository.save(newReceivedNote);

                return new ResponseEntity<>(newReceivedNote, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
