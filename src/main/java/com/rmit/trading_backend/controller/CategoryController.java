package com.rmit.trading_backend.controller;

import com.rmit.trading_backend.model.product.Category;
import com.rmit.trading_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CategoryController {
    //TODO:
    // Category CRUD
    @Autowired
    private CategoryRepository categoryRepository;

    //Get all categories
    @GetMapping("/ordering")
    public ResponseEntity<List<Category>> getAllCategory() {
        try {
            if (categoryRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Post new category
    @PostMapping("/category")
    public ResponseEntity<List<Category>> addCategory(@RequestBody List<Category> categories) {
        try {
            List<Category> categoryList = categoryRepository.saveAll(categories);
            return new ResponseEntity<>(categoryList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
