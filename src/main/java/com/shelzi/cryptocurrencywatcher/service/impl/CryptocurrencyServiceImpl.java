package com.shelzi.cryptocurrencywatcher.service.impl;

import com.shelzi.cryptocurrencywatcher.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
        //return cryptocurrencyDao.read();
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