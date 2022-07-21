package com.shelzi.cryptocurrencywatcher.model.dao.impl;

import com.shelzi.cryptocurrencywatcher.entity.Cryptocurrency;
import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.dao.UserDao;
import com.shelzi.cryptocurrencywatcher.model.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> readAllUsers() {
        String SQL = "SELECT * FROM user";
        return jdbcTemplate.query(SQL, new UserMapper());
    }
}
