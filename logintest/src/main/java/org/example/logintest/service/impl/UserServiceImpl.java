package org.example.logintest.service.impl;

import org.example.logintest.mapper.UserMapper;
import org.example.logintest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.logintest.pojo.User;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectUserById(Integer user_id) {
        return userMapper.selectUserById(user_id);
    }
    @Override
    public User selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    @Override
    public void register(String username, String password, String email, String role) {
        userMapper.register(username,password,"123@com","admin");
    }

}
