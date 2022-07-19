package com.shelzi.cryptocurrencywatcher.dao.mapper;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CryptocurrencyMapper implements ResultSetExtractor<List<Cryptocurrency>> {

    @Override
    public List<Cryptocurrency> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String symbol = rs.getString("symbol");
            cryptocurrencies.add(new Cryptocurrency(id, symbol));
        }
        return cryptocurrencies;
    }
}
