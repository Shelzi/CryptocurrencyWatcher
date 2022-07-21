package com.shelzi.cryptocurrencywatcher.model.dao.mapper;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User.Builder()
                .withId(rs.getLong("id"))
                .withName(rs.getString("name"))
                .withSavedCrypto(new Cryptocurrency(rs.getLong("start_currency_price")))
                .build();
    }
}