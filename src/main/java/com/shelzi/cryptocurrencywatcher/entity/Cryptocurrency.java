package com.shelzi.cryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.*;
import com.shelzi.cryptocurrencywatcher.util.PriceUsdConverter;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {
    @JsonProperty("id")
    private long id;
    @JsonProperty("symbol")
    private String symbol;
    private long priceUsd;

    public Cryptocurrency() {
    }

    public Cryptocurrency(long priceUsd) {
        this.priceUsd = priceUsd;
    }

    public long getId() {
        return id;
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

    public static class Builder {
        private Cryptocurrency cryptocurrency;

        public Builder() {
            cryptocurrency = new Cryptocurrency();
        }

        public Builder withId(long id){
            cryptocurrency.id = id;
            return this;
        }

        public Builder withSymbol(String symbol){
            cryptocurrency.symbol= symbol;
            return this;
        }

        public Builder withPriceUsd(long priceUsd){
            cryptocurrency.priceUsd = priceUsd;
            return this;
        }

        public Cryptocurrency build(){
            return cryptocurrency;
        }

    }

    @Override
    public String toString() {
        return "Cryptocurrency{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", priceUsd='" + PriceUsdConverter.pennyToUsd(priceUsd) + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptocurrency cryptocurrency = (Cryptocurrency) o;
        return id == cryptocurrency.id
                && Objects.equals(symbol, cryptocurrency.symbol)
                && priceUsd == cryptocurrency.priceUsd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, priceUsd);
    }
}