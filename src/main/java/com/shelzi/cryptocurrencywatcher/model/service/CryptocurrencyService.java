package com.shelzi.cryptocurrencywatcher.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;

import java.util.List;

public interface CryptocurrencyService {
    boolean create(User user);

    Cryptocurrency read(long id) throws JsonProcessingException;

    boolean update(Cryptocurrency cryptoCurrency);

    boolean delete(long id);

    List<Cryptocurrency> readAllAvailableCryptocurrencies();

    Cryptocurrency readNewPrice(long id) throws JsonProcessingException;

    void checkDifferenceInPrices();
}