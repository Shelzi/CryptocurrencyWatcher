package com.shelzi.cryptocurrencywatcher.model.service.impl;

import com.shelzi.cryptocurrencywatcher.entity.User;
import com.shelzi.cryptocurrencywatcher.model.dao.UserDao;
import com.shelzi.cryptocurrencywatcher.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> readAllUsers() {
        return userDao.readAllUsers();
    }
}