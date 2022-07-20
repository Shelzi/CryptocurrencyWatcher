package com.shelzi.cryptocurrencywatcher;

import com.shelzi.cryptocurrencywatcher.util.CryptocurrencyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CryptocurrencyWatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptocurrencyWatcherApplication.class, args);
    }
}