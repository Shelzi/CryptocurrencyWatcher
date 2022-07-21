package com.shelzi.cryptocurrencywatcher.model.dao.impl;

import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.model.dao.mapper.CryptocurrencyMapper;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CryptocurrencyDaoImpl implements CryptocurrencyDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_FROM_CRYPTOCURRENCY = "SELECT * FROM cryptocurrency";
    private static final String SELECT_ALL_FROM_CRYPTOCURRENCY_WITH_ID = "SELECT * FROM cryptocurrency WHERE crypto_id=?";
    private static final String INSERT_INTO_USER_NAME_PRICE_CURRENCY = "INSERT INTO user (name, start_curency_price, currency_id_fk) values (?,?,?)";
    private static final String UPDATE_CURRENCY_PRICE = "UPDATE cryptocurrency SET price_usd = ? where crypto_id = ?";

    @Autowired
    public CryptocurrencyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cryptocurrency> readAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_CRYPTOCURRENCY, new CryptocurrencyMapper());
    }

    @Override
    public Optional<Cryptocurrency> read(long id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(SELECT_ALL_FROM_CRYPTOCURRENCY_WITH_ID, new CryptocurrencyMapper(), id));
    }

    @Override
    public boolean create(User user, Cryptocurrency cryptocurrency) {
        return (jdbcTemplate.update(INSERT_INTO_USER_NAME_PRICE_CURRENCY,
                user.getName(), cryptocurrency.getPriceUsd(), cryptocurrency.getId()) == 1);
    }

    @Override
    public boolean update(Cryptocurrency cryptocurrency) {
        return (jdbcTemplate.update(UPDATE_CURRENCY_PRICE,
                cryptocurrency.getPriceUsd(), cryptocurrency.getId()) == 1);
    }
}