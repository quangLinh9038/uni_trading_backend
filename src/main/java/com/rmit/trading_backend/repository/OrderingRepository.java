package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Provider;
import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.ordering.Ordering;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {

    List<Ordering> findAllByStaff(Staff staff);

    List<Ordering> findAllByProvider(Provider provider);

    List<Ordering> findAllByOrderedDate(Date orderedDate);

    List<Ordering> findAllByOrderedDateBetween(Date startDate, Date endDate);
}
