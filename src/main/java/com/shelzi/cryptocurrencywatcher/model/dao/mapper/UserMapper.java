package com.shelzi.cryptocurrencywatcher.model.dao.mapper;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    private static final String SQL_COLUMN_CRYPTOCURRENCY_ID = "crypto_id";
    private static final String SQL_COLUMN_USER_ID = "user_id";
    private static final String SQL_COLUMN_CRYPTOCURRENCY_NAME = "name";
    private static final String SQL_COLUMN_CRYPTOCURRENCY_START_CURRENCY_PRICE = "start_currency_price";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User.Builder()
                .withId(rs.getLong(SQL_COLUMN_USER_ID))
                .withName(rs.getString(SQL_COLUMN_CRYPTOCURRENCY_NAME))
                .withSavedCrypto(
                        new Cryptocurrency
                                .Builder()
                                .withId(rs.getLong(SQL_COLUMN_CRYPTOCURRENCY_ID))
                                .withPriceUsd(rs.getLong(SQL_COLUMN_CRYPTOCURRENCY_START_CURRENCY_PRICE))
                                .build())
                .build();
    }
}