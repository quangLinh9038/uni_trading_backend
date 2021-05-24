package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")

public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    //GET ALL STAFFS
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

    // GET STAFFS BY NAME
    @GetMapping("/staffById/{id}")
    public ResponseEntity<List<Staff>> getStaffById(@PathVariable("id") int id) {
        try {
            if (staffRepository.findStaffByIdContaining(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(staffRepository.findStaffByIdContaining(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET STAFFS BY NAME
    @GetMapping("/staffByName/{name}")
    public ResponseEntity<List<Staff>> getStaffByName(@PathVariable("name") String name) {
        try {
            if (staffRepository.findStaffByNameContaining(name).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(staffRepository.findStaffByNameContaining(name), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST STAFF
    @PostMapping("/staffs")
    public ResponseEntity<List<Staff>> addCategory(@RequestBody List<Staff> staff) {
        try {
            List<Staff> _staffs = staffRepository.saveAll(staff);

            return new ResponseEntity<>(_staffs, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/staffs")
    public ResponseEntity<String> deleteAllStaff() {
        try {
            if (staffRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty staff list", HttpStatus.NO_CONTENT);
            }
            staffRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE STAFF BY ID
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable("id") int id) {
        try {
            if (staffRepository.findById(id).isPresent()) {
                staffRepository.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("/staff/{id}")
    public ResponseEntity<Staff> updateStaffById(@PathVariable("id") int id, @RequestBody Staff staff) {
        Optional<Staff> updatedStaff = staffRepository.findById(id);

        if (updatedStaff.isPresent()) {
            Staff _staff = updatedStaff.get();

            _staff.setName(staff.getName());
            _staff.setPhone(staff.getPhone());
            _staff.setAddress(staff.getAddress());
            _staff.setEmail(staff.getEmail());
            _staff.setFax(staff.getFax());
            return new ResponseEntity<>(staffRepository.save(_staff), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 

}
