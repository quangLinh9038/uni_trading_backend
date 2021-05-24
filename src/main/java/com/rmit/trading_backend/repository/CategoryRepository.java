package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryByName(String name);


}
