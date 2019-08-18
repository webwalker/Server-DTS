package com.xujian.sample.service;

import com.xujian.sample.model.User;

import java.util.List;

public interface UsersService {
    void addUser(User user);

    List<User> findUserAll();

    User findUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);
} 
