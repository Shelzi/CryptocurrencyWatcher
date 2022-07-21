package com.shelzi.cryptocurrencywatcher.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:apiUrl.properties")
@Component
public class RequestExecutor {
    @Value("${url.cryptocurrency-by-id}")
    private String urlReadNewPriceById;

    public ResponseEntity<String> readNewPriceFromApi(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity( urlReadNewPriceById+id, String.class);
    }
}
