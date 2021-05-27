package com.rmit.trading_backend.ordering.repository;

import com.rmit.trading_backend.actor.model.Provider;
import com.rmit.trading_backend.actor.model.Staff;
import com.rmit.trading_backend.ordering.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    List<Ordering> findAllByStaff(Staff staff);

    List<Ordering> findAllByProvider(Provider provider);

    List<Ordering> findAllByOrderedDate(Date orderedDate);

    List<Ordering> findAllByOrderedDateBetween(Date startDate, Date endDate);
}
