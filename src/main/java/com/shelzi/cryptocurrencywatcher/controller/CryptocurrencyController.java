package com.shelzi.cryptocurrencywatcher.controller;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class CryptocurrencyController {
    private final CryptocurrencyService cryptocurrencyService;

    @Autowired
    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @PostMapping(value = "/сreate")
    public ResponseEntity<?> create(@RequestBody User user, Cryptocurrency cryptocurrency) {
        cryptocurrencyService.create(user, cryptocurrency);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cryptocurrency")
    public ResponseEntity<List<Cryptocurrency>> read() {
        final List<Cryptocurrency> cryptocurrencyList = cryptocurrencyService.readAllAvailableCryptocurrencies();
        RestTemplate restTemplate = new RestTemplate();
        Cryptocurrency cryptocurrency = restTemplate.getForObject("https://api.coinlore.net/api/ticker/?id=90", Cryptocurrency.class);
        return cryptocurrencyList != null && !cryptocurrencyList.isEmpty()
                ? new ResponseEntity<>(cryptocurrencyList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cryptocurrency/{id}")
    public ResponseEntity<Cryptocurrency> read(@PathVariable(name = "id") int id) {
        final Cryptocurrency cryptocurrency = cryptocurrencyService.read(id);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cryptocurrency> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("https://api.coinlore.net/api/ticker/?id=" + id, HttpMethod.GET, entity, Cryptocurrency.class);

        /*return cryptocurrency != null
                ? new ResponseEntity<>(cryptocurrency, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
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