package com.rmit.trading_backend.actor.repository;

import com.rmit.trading_backend.actor.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StaffRepository extends JpaRepository<Staff, Long> {


    Optional<Staff> findStaffByEmail(String email);

    Optional<Staff> findStaffByNameContains(String staffName);

    List<Staff> findStaffByNameContaining(String name);

}
