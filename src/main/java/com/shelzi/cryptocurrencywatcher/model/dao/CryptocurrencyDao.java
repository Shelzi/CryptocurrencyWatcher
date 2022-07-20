package com.shelzi.cryptocurrencywatcher.model.dao;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;

import java.util.List;

public interface CryptocurrencyDao {
    List<Cryptocurrency> readAll();
    Cryptocurrency read(int id);
}