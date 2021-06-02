package com.rmit.trading_backend.product.repository;

import com.rmit.trading_backend.product.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
