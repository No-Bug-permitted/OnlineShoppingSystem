package org.example.logintest.service;

import org.example.logintest.pojo.User;
public interface UserService {
    User selectUserById(Integer user_id);
    User selectUserByName(String username);

    void register(String username, String password, String email, String phone);
}
