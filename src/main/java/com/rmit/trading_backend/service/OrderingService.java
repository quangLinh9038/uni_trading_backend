package com.rmit.trading_backend.service;


import com.rmit.trading_backend.model.actor.Provider;
import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.repository.actor.repository.ProviderRepository;
import com.rmit.trading_backend.repository.actor.repository.StaffRepository;
import com.rmit.trading_backend.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderingService {

    @Autowired
    OrderingRepository orderingRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ProviderRepository providerRepository;

    // Adding a new orders
    public void addNewOrder(List<Ordering> orderings) {

        List<Ordering> orderingList = new ArrayList<>();

        for (Ordering order : orderings) {

            // check valid Provider and Staff
            // with email which is more unique than name
            // and more friendly than "id"
            Optional<Provider> providerData = providerRepository.findProviderByEmail(order.getProvider().getEmail());

            Optional<Staff> staffData = staffRepository.findStaffByEmail(order.getStaff().getEmail());

            if (providerData.isPresent() && staffData.isPresent()) {

                Staff _staff = staffData.get();
                Provider _provider = providerData.get();

                order.setProvider(_provider);
                order.setStaff(_staff);

                orderingList.add(order);
            }
            System.out.println("Provider or staff not found");
        }
        orderingRepository.saveAll(orderingList);
        System.out.println("Success");
    }
}
