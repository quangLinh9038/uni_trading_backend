package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.product.Category;
import com.rmit.trading_backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findProductByNameContaining(String name);

    Optional<Product> findProductsByBrandContaining(String brand);

    Optional<Product> findProductsByCategory(Category category);

}
