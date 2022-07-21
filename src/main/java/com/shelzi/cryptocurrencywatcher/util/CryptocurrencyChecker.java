package com.shelzi.cryptocurrencywatcher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import com.shelzi.cryptocurrencywatcher.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CryptocurrencyChecker extends Thread {
    private static final Logger logger = LogManager.getLogger();
    private final CryptocurrencyService cryptocurrencyService;
    private final UserService userService;
    private static final int ONE_MINUTE = 60000;
    private boolean isRequesting;

    public void setRequesting(boolean requesting) {
        isRequesting = requesting;
    }

    @Autowired
    public CryptocurrencyChecker(CryptocurrencyService cryptocurrencyService, UserService userService) {
        this.cryptocurrencyService = cryptocurrencyService;
        this.userService = userService;

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
                    cryptocurrencyService.update(cryptocurrencyService.readNewPrice(id));
                }

                List<User> users = userService.readAllUsers();
                for (User user : users) {
                    checkPriceDifference(user);
                }
                sleep(ONE_MINUTE);
            }
        } catch (InterruptedException | JsonProcessingException e) {
            logger.log(Level.ERROR, "Exception: " + e.getMessage());
        }
    }

    @PostConstruct
    public void cryptocurrencyCheckerDaemon() {
        setDaemon(true);
        setRequesting(true);
        start();
    }

    private void checkPriceDifference(User user) {
        try {
            long userStartCryptoPrice = user.getSavedCrypto().getPriceUsd();
            long actualPrice;
            actualPrice = cryptocurrencyService.read(user.getSavedCrypto().getId()).getPriceUsd();
            double priceDifferenceResult = (actualPrice - userStartCryptoPrice) / (double) userStartCryptoPrice * 100;
            if (priceDifferenceResult > 1) {
                logger.log(Level.WARN, "Price changed. Crypto ID: "
                        + user.getSavedCrypto().getId()
                        + " Name: "
                        + user.getName()
                        + " Changed by: "
                        + priceDifferenceResult + "%");
            }
        } catch (JsonProcessingException e) {
            logger.log(Level.ERROR, "Exception: " + e.getMessage());
        }
    }
}