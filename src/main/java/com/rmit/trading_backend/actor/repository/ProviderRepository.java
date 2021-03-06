package com.rmit.trading_backend.actor.repository;

import com.rmit.trading_backend.actor.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {


    Optional<Provider> findProviderByEmail(String email);

    Optional<Provider> findProviderByName(String name);
}
