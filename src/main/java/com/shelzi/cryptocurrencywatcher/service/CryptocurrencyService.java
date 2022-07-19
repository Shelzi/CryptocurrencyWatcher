package com.shelzi.cryptocurrencywatcher.service;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;

import java.util.List;

public interface CryptocurrencyService {
    boolean create(User user, Cryptocurrency cryptocurrency);

    Cryptocurrency read(int id);

    boolean update(User user, Cryptocurrency cryptoCurrency);

    boolean delete(int id);

    List<Cryptocurrency> readAllAvailableCryptocurrencies();
}