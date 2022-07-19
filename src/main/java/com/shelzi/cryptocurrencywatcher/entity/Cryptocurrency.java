package com.shelzi.cryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {
    private long id;
    private String symbol;
    private long priceUsd;

    public Cryptocurrency() {
    }

    public Cryptocurrency(long id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    @JsonCreator
    public Cryptocurrency(long id, String symbol, long priceUsd) {
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

    public long getPriceUsd() {
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