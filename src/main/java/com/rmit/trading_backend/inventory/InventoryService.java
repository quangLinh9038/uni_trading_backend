package com.rmit.trading_backend.inventory;


import com.rmit.trading_backend.product.model.Product;
import com.rmit.trading_backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class InventoryService {

    @Autowired
    ProductRepository productRepository;

    public void returnReceivedInventory(Date startDate, Date endDate) {

        List<Product> productList = productRepository.findAll();

        List<Inventory> inventories = new ArrayList<>();

    }


}
