package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.product.Product;
import com.rmit.trading_backend.repository.CategoryRepository;
import com.rmit.trading_backend.repository.ProductRepository;
import com.rmit.trading_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


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
    public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
        try {
            if (productRepository.findProductByNameContaining(name).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productRepository.findProductByNameContaining(name), HttpStatus.OK);
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

    //GET PRODUCT BY CATEGORY ID
    @GetMapping("/productByCategoryId/{id}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable(value = "id") int categoryID) {
        try {
            if (productRepository.findAllProductByCategoryId(categoryID).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productRepository.findAllProductByCategoryId(categoryID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //GET PRODUCT BY CATEGORY NAME
    @GetMapping("/productByCategoryName/{name}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable(value = "name") String categoryName) {
        try {
            if (productRepository.findAllProductByCategoryName(categoryName).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productRepository.findAllProductByCategoryName(categoryName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // POST list of products
    @PostMapping("/products")
    public ResponseEntity<List<Product>> addList(@RequestBody List<Product> products) {

        try {
            if (productService.addProduct(products)) {
                return new ResponseEntity<>(products, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        } catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
