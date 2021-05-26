package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff findStaffById(int id);
    Staff findStaffByEmail(String email);
    Staff findStaffByNameContains(String name);

    List<Staff> findStaffByIdContaining(int id);
    List<Staff> findStaffByNameContaining(String name);

}
