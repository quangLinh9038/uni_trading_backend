package com.rmit.trading_backend.repository.product.repository;

import com.rmit.trading_backend.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByName(String cateName);
}
