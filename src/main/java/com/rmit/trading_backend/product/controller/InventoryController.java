package com.rmit.trading_backend.product.controller;

import com.rmit.trading_backend.product.model.Inventory;
import com.rmit.trading_backend.product.model.Product;
import com.rmit.trading_backend.product.repository.InventoryRepository;
import com.rmit.trading_backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "hhtp://localhost:9090")
@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;


    // GET INVENTORIES
    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getInventory(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
    ) {
        try {
            List<Inventory> inventoryList = new ArrayList<>();

            List<Product> productList = productRepository.findAll();
            System.out.println("PRODUCT LIST   " + productList);

            if (productList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for (Product product : productList) {

                int received;
                int delivery;


                int productId = product.getId();
                System.out.println("********PRODUCT ID:   " + productId);

                String productName = product.getName();

                // return received
                Integer checkReceive = productRepository.calculateTotalReceivedQuantity(productId, endDate);
                received = Objects.requireNonNullElse(checkReceive, 0);
                System.out.println("********RECEIVED quantity:   " + received);

                Integer checkDelivery = productRepository.calculateTotalDeliveryQuantity(productId, startDate, endDate);
                delivery = Objects.requireNonNullElse(checkDelivery, 0);
                System.out.println("********DELIVERY quantity:   " + delivery);


                int balance = received - delivery;

                Inventory inventory = new Inventory();

                inventory.setProduct(productName);
                inventory.setReceived(received);
                inventory.setDelivery(delivery);
                inventory.setBalance(balance);

                inventoryList.add(inventory);
            }

            inventoryRepository.saveAll(inventoryList);

            if (inventoryList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(inventoryList, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
