package org.example.logintest.service;

import org.example.logintest.pojo.User;
public interface UserService {
    User queryUserByPhone(String phone);

    void register(String username, String password, String user_role, String phone);

}
