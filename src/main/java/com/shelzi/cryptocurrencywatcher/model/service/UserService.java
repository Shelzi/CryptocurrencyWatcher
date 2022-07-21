package com.shelzi.cryptocurrencywatcher.model.service;

import com.shelzi.cryptocurrencywatcher.entity.User;

import java.util.List;

public interface UserService {
    List<User> readAllUsers();
}
