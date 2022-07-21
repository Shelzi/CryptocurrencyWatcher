package com.shelzi.cryptocurrencywatcher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptocurrencyController {
    private final CryptocurrencyService cryptocurrencyService;

    @Autowired
    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        cryptocurrencyService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cryptocurrency")
    public ResponseEntity<List<Cryptocurrency>> read() {
        final List<Cryptocurrency> cryptocurrencyList = cryptocurrencyService.readAllAvailableCryptocurrencies();
        return (!CollectionUtils.isEmpty(cryptocurrencyList)
                ? new ResponseEntity<>(cryptocurrencyList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/cryptocurrency/{id}")
    public ResponseEntity<Cryptocurrency> read(@PathVariable(name = "id") int id) throws JsonProcessingException {
        final Cryptocurrency cryptocurrency = cryptocurrencyService.read(id);
        return (cryptocurrency != null
                ? new ResponseEntity<>(cryptocurrency, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}