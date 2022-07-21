package com.shelzi.cryptocurrencywatcher.model.dao;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;

import java.util.List;
import java.util.Optional;

public interface CryptocurrencyDao {
    List<Cryptocurrency> readAll();

    Optional<Cryptocurrency> read(long id);

    boolean create(User user, Cryptocurrency cryptocurrency);

    boolean update(Cryptocurrency cryptocurrency);
}