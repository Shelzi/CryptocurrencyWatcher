package com.shelzi.cryptocurrencywatcher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CryptocurrencyChecker extends Thread {
    private final String CRYPTOCURRENCY_BY_ID_API_URL = "https://api.coinlore.net/api/ticker/?id=";
    private final CryptocurrencyService cryptocurrencyService;
    private boolean isRequesting;

    public void setRequesting(boolean requesting) {
        isRequesting = requesting;
    }

    @Autowired
    public CryptocurrencyChecker(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @Override
    public void run() {
        try {
            while (isRequesting) {
                List<Long> availableCryptocurrenciesId =
                        cryptocurrencyService.
                                readAllAvailableCryptocurrencies().stream()
                                .map(Cryptocurrency::getId)
                                .toList();
                for (long id : availableCryptocurrenciesId) {
                    cryptocurrencyService.update(cryptocurrencyService.readNewPriceFromApi(id));
                }
                sleep(60000);
            }
        } catch (InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void cryptocurrencyCheckerDaemon() {
        setDaemon(true);
        setRequesting(true);
        start();
    }
}