package com.example.trading_backend.repository;

import com.example.trading_backend.model.Category;
import com.example.trading_backend.model.Product;
import com.example.trading_backend.model.actor.Customer;
import com.example.trading_backend.model.actor.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Integer> {
    Optional<Product> findProductByNameContaining(String name);
    Optional<Product> findProductsByBrandContaining(String brand);

    Optional<Product> findProductsByCategory(Category category);
    Optional<Product> findProductsByProvider(Provider provider);
}
