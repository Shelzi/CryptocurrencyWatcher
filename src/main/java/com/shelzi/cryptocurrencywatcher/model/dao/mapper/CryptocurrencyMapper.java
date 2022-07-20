package com.shelzi.cryptocurrencywatcher.model.dao.mapper;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CryptocurrencyMapper implements RowMapper<Cryptocurrency> {
    @Override
    public Cryptocurrency mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setId(rs.getLong("id"));
        cryptocurrency.setSymbol(rs.getString("symbol"));
        cryptocurrency.setPriceUsd(rs.getLong("price_usd"));
        return cryptocurrency;
    }
}