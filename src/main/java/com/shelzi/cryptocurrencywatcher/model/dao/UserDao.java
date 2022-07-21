package com.shelzi.cryptocurrencywatcher.model.dao;

import com.shelzi.cryptocurrencywatcher.entity.User;

import java.util.List;

public interface UserDao {
    List<User> readAllUsers();
}
