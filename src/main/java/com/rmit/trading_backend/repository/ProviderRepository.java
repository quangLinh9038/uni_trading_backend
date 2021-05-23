package com.rmit.trading_backend.repository;

import com.rmit.trading_backend.model.actor.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
