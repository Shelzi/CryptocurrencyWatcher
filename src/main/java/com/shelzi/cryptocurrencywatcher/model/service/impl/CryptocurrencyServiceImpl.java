package com.shelzi.cryptocurrencywatcher.model.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelzi.cryptocurrencywatcher.model.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final CryptocurrencyDao cryptocurrencyDao;

    @Autowired
    public CryptocurrencyServiceImpl(CryptocurrencyDao cryptocurrencyDao) {
        this.cryptocurrencyDao = cryptocurrencyDao;
    }

    @Override
    public boolean create(User user, Cryptocurrency cryptoCurrency) {
        return false;
    }

    @Override
    public Cryptocurrency read(int id) {
        return cryptocurrencyDao.read(id);
    }

    @Override
    public boolean update(User user, Cryptocurrency cryptoCurrency) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Cryptocurrency> readAllAvailableCryptocurrencies() {
        return cryptocurrencyDao.readAll();
    }
}