package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff findStaffByEmail(String email);

    Staff findStaffByNameContains (String staffName);
}
