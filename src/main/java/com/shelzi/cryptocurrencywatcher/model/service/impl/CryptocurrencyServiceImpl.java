package com.shelzi.cryptocurrencywatcher.model.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelzi.cryptocurrencywatcher.exception.ServiceException;
import com.shelzi.cryptocurrencywatcher.model.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import com.shelzi.cryptocurrencywatcher.util.JsonFormatter;
import com.shelzi.cryptocurrencywatcher.util.PriceUsdConverter;
import com.shelzi.cryptocurrencywatcher.web.RequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {
    private final CryptocurrencyDao cryptocurrencyDao;

    public static final String JSON_PRICE_USD = "price_usd";

    @Autowired
    public CryptocurrencyServiceImpl(CryptocurrencyDao cryptocurrencyDao) {
        this.cryptocurrencyDao = cryptocurrencyDao;
    }

    @Override
    public boolean create(User user) {
        Optional<Cryptocurrency> optionalCryptocurrency = cryptocurrencyDao.read(user.getSavedCrypto().getId());
        if (optionalCryptocurrency.isPresent()) {
            return cryptocurrencyDao.create(user, optionalCryptocurrency.get());
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public Cryptocurrency read(long id) {
        Optional<Cryptocurrency> optionalCryptocurrency = cryptocurrencyDao.read(id);
        if (optionalCryptocurrency.isPresent()) {
            return optionalCryptocurrency.get();
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public boolean update(Cryptocurrency cryptoCurrency) {
        return cryptocurrencyDao.update(cryptoCurrency);
    }

    @Override
    public List<Cryptocurrency> readAllAvailableCryptocurrencies() {
        return cryptocurrencyDao.readAll();
    }

    @Override
    public Cryptocurrency readNewPrice(long id) throws JsonProcessingException {
        RequestExecutor executor = new RequestExecutor();
        Optional<String> response = Optional.ofNullable(executor.readNewPriceFromApi(id).getBody());
        if (response.isPresent()) {
            String body = JsonFormatter.formatJsonObject(response.get());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readValue(body, JsonNode.class);
            Cryptocurrency crypto = mapper.readValue(body, Cryptocurrency.class);
            /*crypto.setPriceUsd(
                    PriceUsdConverter.UsdToPenny(rootNode.get(rootNode.size() - 1).get(JSON_PRICE_USD).textValue()));*/
            crypto.setPriceUsd(
                    PriceUsdConverter.UsdToPenny(rootNode.get(JSON_PRICE_USD).textValue()));
            return crypto;
        } else {
            throw new ServiceException();
        }
    }
}