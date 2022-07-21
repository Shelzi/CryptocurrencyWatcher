package com.shelzi.cryptocurrencywatcher.model.dao.impl;

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

    private static final String SELECT_ALL_FROM_USER = "SELECT * FROM user";

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> readAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_FROM_USER, new UserMapper());
    }
}