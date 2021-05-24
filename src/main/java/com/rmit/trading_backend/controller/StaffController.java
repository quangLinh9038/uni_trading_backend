package com.rmit.trading_backend.controller;


import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")

public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    //GET requests
    @GetMapping("/staffs")
    public ResponseEntity<List<Staff>> getAllStaffs() {
        try {
            if (staffRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(staffRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST request
    @PostMapping("/staffs")
    public ResponseEntity<List<Staff>> addCategory(@RequestBody List<Staff> staff) {
        try {
            List<Staff> _staffs = staffRepository.saveAll(staff);

            return new ResponseEntity<>(_staffs, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
