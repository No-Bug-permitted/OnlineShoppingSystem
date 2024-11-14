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
    public User queryUserByPhone(String phone) {
        return userMapper.queryUserByPhone(phone);
    }

    @Override
    public void register(String username, String password, String user_role, String phone) {
        if(username!=null){
            userMapper.register(username,password,user_role,phone);
        }
        else{
            userMapper.register(phone,password,user_role,phone);
        }
    }

}
