package com.rmit.trading_backend.service;


import com.rmit.trading_backend.model.product.Category;
import com.rmit.trading_backend.model.product.Product;
import com.rmit.trading_backend.repository.CategoryRepository;
import com.rmit.trading_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // ADD NEW PRODUCTS
    // adding a list or one product
    public boolean addProduct(List<Product> productList) {
        for (Product product : productList) {

            // checking valid category matched with requests of adding category into product by cate_name
            Category category = categoryRepository.findCategoryByName(product.getCategory().getName());
            Optional<Category> checkCategory = Optional.ofNullable(category);

            if (checkCategory.isPresent()) {

                product.setCategory(category);

                productRepository.saveAll(productList);

                System.out.println("Add product successfully");
                return true;
            }
            System.out.println("Category not found");
        }
        return false;
    }
}
