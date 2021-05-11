package com.example.trading_backend.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getList();
    Optional<T> getById();
    void delete();
    void update();
}
