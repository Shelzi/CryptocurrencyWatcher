package com.shelzi.cryptocurrencywatcher.model.dao.impl;

import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.model.dao.mapper.CryptocurrencyMapper;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CryptocurrencyDaoImpl implements CryptocurrencyDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CryptocurrencyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cryptocurrency> readAll() {
        String SQL = "SELECT * FROM cryptocurrency";
        List<Cryptocurrency> cryptocurrencies = jdbcTemplate.query(SQL, new CryptocurrencyMapper());
        return cryptocurrencies;
    }

    public Cryptocurrency read(long id) {
        String SQL = "SELECT * FROM cryptocurrency WHERE id = ?";
        Cryptocurrency cryptocurrencies = jdbcTemplate.queryForObject(SQL, new CryptocurrencyMapper(), id);
        return cryptocurrencies;
    }

    @Override
    public boolean create(User user, Cryptocurrency cryptocurrency) {
        String SQL = "INSERT INTO user (name, start_curency_price, currency_id_fk) values (?,?,?)";
        return (jdbcTemplate.update(SQL, user.getName(), cryptocurrency.getPriceUsd(), cryptocurrency.getId()) == 1);
    }

    @Override
    public boolean update(Cryptocurrency cryptocurrency) {
        String SQL = "UPDATE cryptocurrency SET price_usd = ? where id = ?";
        return (jdbcTemplate.update(SQL, cryptocurrency.getPriceUsd(), cryptocurrency.getId()) == 1);
    }
}
