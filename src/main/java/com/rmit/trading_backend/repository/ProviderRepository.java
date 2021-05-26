package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {


    Optional<Provider> findProviderByEmail(String email);

    Optional<Provider> findProviderByName(String name);
}
