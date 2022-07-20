package com.shelzi.cryptocurrencywatcher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class CryptocurrencyController {
    private final CryptocurrencyService cryptocurrencyService;

    @Autowired
    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody User user, Cryptocurrency cryptocurrency) {
        cryptocurrencyService.create(user, cryptocurrency);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cryptocurrency")
    public ResponseEntity<List<Cryptocurrency>> read() throws JsonProcessingException {
        final List<Cryptocurrency> cryptocurrencyList = cryptocurrencyService.readAllAvailableCryptocurrencies();
        RestTemplate restTemplate = new RestTemplate();
        String jsonStringArray = restTemplate.getForObject("https://api.coinlore.net/api/tickers/", String.class);
        ObjectMapper mapper = new ObjectMapper();
        String s = jsonStringArray.substring(jsonStringArray.indexOf("["), jsonStringArray.indexOf("]") + 1);
        List<Cryptocurrency> studentList = mapper.readValue(s, new TypeReference<ArrayList<Cryptocurrency>>() {
        });
        return !studentList.isEmpty()
                ? new ResponseEntity<>(studentList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cryptocurrency/{id}")
    public ResponseEntity<Cryptocurrency> read(@PathVariable(name = "id") int id) throws JsonProcessingException {
        final Cryptocurrency cryptocurrency = cryptocurrencyService.read(id);
        return cryptocurrency != null
                ? new ResponseEntity<>(cryptocurrency, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> cryptocurrency(@PathVariable(name = "id") int id, @RequestBody User user, Cryptocurrency cryptocurrency) {
        final boolean updated = cryptocurrencyService.update(user, cryptocurrency);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = cryptocurrencyService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}