package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.product.Category;
import com.rmit.trading_backend.model.product.Product;
import com.rmit.trading_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //Get all products
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

    //Get one by name
    @GetMapping("/productByName/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {
        try {
            Optional<Product> product = productRepository.findProductByNameContaining(name);
            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get products by brand
    @GetMapping("/productByBrand/{brand}")
    public ResponseEntity<Product> getProductByBrand(@PathVariable("brand") String brand) {
        try {
            Optional<Product> product = productRepository.findProductsByBrandContaining(brand);
            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get products by category
    @GetMapping("/productByCategory/{category}")
    public ResponseEntity<Product> getProductByCategory(@PathVariable("category") Category category) {
        try {
            Optional<Product> product = productRepository.findProductsByCategory(category);
            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get products by provider
//    @GetMapping("/productByProvider/{provider}")
//    public ResponseEntity<Product> getProductByProvider(@PathVariable("provider") Provider provider) {
//        try {
//            Optional<Product> product = productRepository.findProductsByProvider(provider);
//            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //Post new product
    @PostMapping("/products")
    public ResponseEntity<List<Product>> addProduct(@RequestBody List<Product> products) {
        try {
            List<Product> productList = productRepository.saveAll(products);
            return new ResponseEntity<>(productList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ONE
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
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
    public ResponseEntity<Product> updateCustomerById(@PathVariable("id") int id, @RequestBody Product product) {
        Optional<Product> updatedProduct = productRepository.findById(id);

        if (updatedProduct.isPresent()) {

            Product _product = updatedProduct.get();

            _product.setName(_product.getName());
            _product.setModel(_product.getModel());
            _product.setBrand(_product.getBrand());
            _product.setPrice(_product.getPrice());
            _product.setDescriptions(_product.getDescriptions());
            _product.setCompany(_product.getCompany());
//            _product.setProvider(_product.getProvider());
            _product.setCategory(_product.getCategory());


            productRepository.save(_product);
            return new ResponseEntity<>(_product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
