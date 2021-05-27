package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.product.Category;
import com.rmit.trading_backend.model.product.Product;
import com.rmit.trading_backend.repository.product.repository.ProductRepository;
import com.rmit.trading_backend.repository.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    //GET ALL PRODUCTS
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            //check empty list
            if (productRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET PRODUCT BY NAME
    @GetMapping("/productByName/{name}")
    public ResponseEntity<Optional<Product>> getProductByName(@PathVariable("name") String name) {
        try {
            Optional<Product> product = productRepository.findProductByNameContaining(name);

            if (product.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET PRODUCT BY BRAND
    @GetMapping("/productByBrand/{brand}")
    public ResponseEntity<List<Product>> getProductByBrand(@PathVariable("brand") String brand) {
        try {
            if (productRepository.findProductsByBrandContaining(brand).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productRepository.findProductsByBrandContaining(brand), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //GET PRODUCT BY CATEGORY NAME
    @GetMapping("/productByCategoryName/{name}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable(value = "name") String categoryName) {
        try {
            if (productRepository.findAllProductByCategoryContaining(categoryName).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productRepository.findAllProductByCategoryContaining(categoryName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST list of products
    @PostMapping("/products")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {

        try {
            List<Product> newProducts = new ArrayList<>();

            for (Product product : products) {

                Optional<Category> category = categoryRepository.findCategoryByName(product.getCategory().getName());

                if (category.isPresent()) {
                    Category _category = category.get();
                    product.setCategory(_category);
                    newProducts.add(product);
                }
            }
            if (newProducts.isEmpty()) {
                return new ResponseEntity<>(newProducts, HttpStatus.NOT_FOUND);
            }
            productRepository.saveAll(newProducts);
            return new ResponseEntity<>(newProducts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE PRODUCT
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        try {
            if (productRepository.findById(id).isPresent()) {
                productRepository.deleteById(id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL
    @DeleteMapping("/products")
    public ResponseEntity<String> deleteAllProduct() {
        try {
            if (productRepository.findAll().isEmpty()) {
                return new ResponseEntity<>("Empty product list", HttpStatus.NO_CONTENT);
            }
            productRepository.deleteAll();
            return new ResponseEntity<>("Delete all successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateCustomerById(@PathVariable("id") long id, @RequestBody Product product) {
        Optional<Product> updatedProduct = productRepository.findById(id);
        try {
            if (updatedProduct.isPresent()) {

                Product _product = updatedProduct.get();

                _product.setName(product.getName());
                _product.setModel(product.getModel());
                _product.setBrand(product.getBrand());
                _product.setPrice(product.getPrice());
                _product.setDescriptions(product.getDescriptions());
                _product.setCompany(product.getCompany());
                _product.setCategory(product.getCategory());

                productRepository.save(product);
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
