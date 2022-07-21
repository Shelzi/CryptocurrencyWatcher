package com.shelzi.cryptocurrencywatcher.model.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelzi.cryptocurrencywatcher.model.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import com.shelzi.cryptocurrencywatcher.util.PriceUsdConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:apiUrl.properties")
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final CryptocurrencyDao cryptocurrencyDao;

    @Autowired
    public CryptocurrencyServiceImpl(CryptocurrencyDao cryptocurrencyDao) {
        this.cryptocurrencyDao = cryptocurrencyDao;
    }

    @Override
    public boolean create(User user) {
        Cryptocurrency freshData = cryptocurrencyDao.read(user.getTrackId());
        return cryptocurrencyDao.create(user, freshData);
    }

    @Override
    public Cryptocurrency read(long id) {
        return cryptocurrencyDao.read(id);
    }

    @Override
    public boolean update(Cryptocurrency cryptoCurrency) {
        return cryptocurrencyDao.update(cryptoCurrency);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Cryptocurrency> readAllAvailableCryptocurrencies() {
        return cryptocurrencyDao.readAll();
    }

    @Override
    public Cryptocurrency readNewPriceFromApi(long id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> s =
                restTemplate.getForEntity("https://api.coinlore.net/api/ticker/?id=" + id, String.class);

        String body = s.getBody().replaceAll("[\\[\\]]", "");
        JsonNode rootNode = mapper.readValue(s.getBody(), JsonNode.class);
        Cryptocurrency crypto = mapper.readValue(body, Cryptocurrency.class);
        crypto.setPriceUsd(
                PriceUsdConverter.UsdToPenny(
                        rootNode.get(rootNode.size() - 1).get("price_usd").textValue()));
        return crypto;
    }
}