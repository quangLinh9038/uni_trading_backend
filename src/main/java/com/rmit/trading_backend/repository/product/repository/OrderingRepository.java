package com.rmit.trading_backend.repository.product.repository;

import com.rmit.trading_backend.model.actor.Provider;
import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.ordering.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    List<Ordering> findAllByStaff(Optional<Staff> staff);

    List<Ordering> findAllByProvider(Provider provider);


}
