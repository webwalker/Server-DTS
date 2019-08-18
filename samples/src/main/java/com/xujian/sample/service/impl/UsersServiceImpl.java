package com.xujian.sample.service.impl;

import com.xujian.sample.mapper.UserMapper;
import com.xujian.sample.model.User;
import com.xujian.sample.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        this.userMapper.insertUser(user);
//        int i = 4 / 0;
//        this.userMapper.insertUser(users);
    }

    @Override
    public List<User> findUserAll() {
        return this.userMapper.selectUsersAll();
    }

    @Override
    public User findUserById(Integer id) {
        return this.userMapper.selectUsersById(id);
    }

    @Override
    public void updateUser(User user) {
        this.userMapper.updateUser(user);
        int i = 4 / 0;
        this.userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        this.userMapper.deleteUserById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void parseBookmarkFile(int userId, InputStream stream, String path) throws Exception {

    }
}
