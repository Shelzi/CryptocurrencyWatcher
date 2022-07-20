package com.shelzi.cryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {
    @JsonAlias("id")
    private long id;
    @JsonAlias("symbol")
    private String symbol;
    @JsonAlias("price_usd")
    private double priceUsd;
    // TODO: 20.07.2022 вернуть long

    public Cryptocurrency() {
    }

    public Cryptocurrency(long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    @JsonCreator
    public Cryptocurrency(@JsonProperty("id") long id, @JsonProperty("symbol") String symbol, @JsonProperty("price_usd") double priceUsd) {
        this.id = id;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(long priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}