package com.shelzi.cryptocurrencywatcher.model.dao;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;

import java.util.List;

public interface CryptocurrencyDao {
    List<Cryptocurrency> readAll();

    Cryptocurrency read(long id);

    boolean create(User user, Cryptocurrency cryptocurrency);

    boolean update(Cryptocurrency cryptocurrency);
}