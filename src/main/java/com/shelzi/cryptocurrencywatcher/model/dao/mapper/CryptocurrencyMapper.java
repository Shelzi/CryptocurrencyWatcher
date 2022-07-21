package com.shelzi.cryptocurrencywatcher.model.dao.mapper;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CryptocurrencyMapper implements RowMapper<Cryptocurrency> {
    @Override
    public Cryptocurrency mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cryptocurrency.Builder()
                .withId(rs.getLong("id"))
                .withSymbol(rs.getString("symbol"))
                .withPriceUsd(rs.getLong("price_usd"))
                .build();
    }
}