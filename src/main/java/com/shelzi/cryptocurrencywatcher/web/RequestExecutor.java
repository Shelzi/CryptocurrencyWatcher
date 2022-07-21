package com.shelzi.cryptocurrencywatcher.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestExecutor {
    private String URL_READ_NEW_PRICE_BY_ID = "https://api.coinlore.net/api/ticker/?id=";

    public ResponseEntity<String> readNewPriceFromApi(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(URL_READ_NEW_PRICE_BY_ID + id, String.class);
    }
}