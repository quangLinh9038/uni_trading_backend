package com.rmit.trading_backend.service;


import com.rmit.trading_backend.model.actor.Provider;
import com.rmit.trading_backend.model.actor.Staff;
import com.rmit.trading_backend.model.ordering.Ordering;
import com.rmit.trading_backend.repository.OrderingRepository;
import com.rmit.trading_backend.repository.ProviderRepository;
import com.rmit.trading_backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //POST method
    //Adding new orders
    public boolean addOrders(List<Ordering> orderList) {
        for (Ordering ordering : orderList) {

            // check valid Provider and Staff
            // with email which is more unique than name
            // and more friendly than "id"
            Provider provider = providerRepository.findProviderByEmail(ordering.getProvider().getEmail());

            // optional return type
            Optional<Provider> optProvider = Optional.ofNullable(provider);


            Staff staff = staffRepository.findStaffByEmail(ordering.getStaff().getEmail());
            Optional<Staff> optStaff = Optional.ofNullable(staff);

            if (optProvider.isPresent() && optStaff.isPresent()) {

                ordering.setProvider(provider);
                ordering.setStaff(staff);

                orderingRepository.saveAll(orderList);

                System.out.println("Add order success!");
                return true;
            }
            System.out.println("Provider or staff not found");
        }
        return false;
    }

}
