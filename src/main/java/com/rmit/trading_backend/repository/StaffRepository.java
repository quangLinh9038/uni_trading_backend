package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StaffRepository extends JpaRepository<Staff, Long> {

//    Staff findStaffById(int id);
    Staff findStaffByEmail(String email);
    Staff findStaffByNameContains(String name);


    List<Staff> findStaffByIdContaining(int id);
    List<Staff> findStaffByNameContaining(String name);

}
