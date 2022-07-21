package com.shelzi.cryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private long id;
    private String name;
    @JsonProperty("track_id")
    private long currencyIdFk;
    private final Cryptocurrency watchCrypto;

    public User(Cryptocurrency watchCrypto) {
        this.watchCrypto = watchCrypto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCurrencyIdFk() {
        return currencyIdFk;
    }

    public void setCurrencyIdFk(long currencyIdFk) {
        this.currencyIdFk = currencyIdFk;
    }

    public Cryptocurrency getWatchCrypto() {
        return watchCrypto;
    }
}