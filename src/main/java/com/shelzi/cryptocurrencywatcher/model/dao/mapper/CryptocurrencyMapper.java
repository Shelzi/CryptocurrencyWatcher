package com.shelzi.cryptocurrencywatcher.model.dao.mapper;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CryptocurrencyMapper implements RowMapper<Cryptocurrency> {
    private static final String SQL_COLUMN_USER_ID = "crypto_id";
    private static final String SQL_COLUMN_USER_SYMBOL = "symbol";
    private static final String SQL_COLUMN_USER_PRICE_USD = "price_usd";

    @Override
    public Cryptocurrency mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Cryptocurrency.Builder()
                .withId(rs.getLong(SQL_COLUMN_USER_ID))
                .withSymbol(rs.getString(SQL_COLUMN_USER_SYMBOL))
                .withPriceUsd(rs.getLong(SQL_COLUMN_USER_PRICE_USD))
                .build();
    }
}