package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.product.Category;
import com.rmit.trading_backend.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByNameContaining(String name);

    List<Product> findProductsByBrandContaining(String brand);

    //    Optional<Product> findProductsByCategory(Category category);
    List<Product> findAllProductByCategoryId(@Param("id")int categoryID);
    List<Product> findAllProductByCategoryName(@Param("name")String categoryName);



}
