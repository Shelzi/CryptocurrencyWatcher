package com.shelzi.cryptocurrencywatcher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CryptocurrencyChecker extends Thread {
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
                System.out.println(cryptocurrencyService.read(90));
                sleep(1000);
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