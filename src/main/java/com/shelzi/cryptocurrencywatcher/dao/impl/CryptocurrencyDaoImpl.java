package com.shelzi.cryptocurrencywatcher.dao.impl;

import com.shelzi.cryptocurrencywatcher.dao.CryptocurrencyDao;
import com.shelzi.cryptocurrencywatcher.dao.mapper.CryptocurrencyMapper;
import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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

    public Cryptocurrency read(int id){
        return null;
    }
}
