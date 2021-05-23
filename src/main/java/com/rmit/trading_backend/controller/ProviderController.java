package com.rmit.trading_backend.controller;


import com.rmit.trading_backend.model.actor.Provider;
import com.rmit.trading_backend.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    //GET requests
    @GetMapping("/providers")
    public ResponseEntity<List<Provider>> getAllProviders() {
        try {
            if (providerRepository.findAll().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(providerRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST request
    @PostMapping("/providers")
    public ResponseEntity<List<Provider>> addProviders(@RequestBody List<Provider> providers) {
        try {
            List<Provider> _providers = providerRepository.saveAll(providers);

            return new ResponseEntity<>(_providers, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
