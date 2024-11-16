package com.example.onlineshoppingsystem.service;

import com.example.onlineshoppingsystem.entity.User;
import com.example.onlineshoppingsystem.repository.UserRepository;
import com.example.onlineshoppingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // 注册新用户
    @Transactional
    public ResponseEntity<String> registerUser(User user) {
        // 判断 phone 是否已存在
        if (userRepository.existsByPhone(user.getPhone())) {
            return new ResponseEntity<>("{\"code\":\"0\", \"msg\":\"Phone number already in use\", \"result\":null}", HttpStatus.BAD_REQUEST);
        }

        // 使用 account 作为用户名，映射到数据库中的 username
        user.setUsername(user.getAccount());

        // 将 account 映射为 phone 存储
        user.setPhone(user.getPhone());

        // 不对密码进行加密，直接使用明文密码
        user.setPassword(user.getPassword());

        // 保存用户信息
        userRepository.save(user);

        // 生成 JWT Token，传递 username、role 和 phone
        String token = jwtUtil.generateToken(user.getUsername(), user.getUserRole(), user.getPhone());

        // 构造返回的注册成功信息
        String result = "{"
                + "\"userId\":\"" + user.getUserId() + "\","
                + "\"phone\":\"" + user.getPhone() + "\","
                + "\"username\":\"" + user.getUsername() + "\","
                + "\"userRole\":\"" + user.getUserRole() + "\","
                + "\"token\":\"" + token + "\","
                + "\"avatar\":\"" + user.getAvatar() + "\","
                + "\"sex\":\"" + user.getSex() + "\""
                + "}";

        // 返回注册成功的 JSON 响应
        return new ResponseEntity<>("{\"code\":\"1\", \"msg\":\"User registered successfully.\", \"result\":" + result + "}", HttpStatus.CREATED);
    }

    // 根据 phone 查找用户
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    // 验证密码的方法
    public boolean checkPassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }

    // 更新用户信息
    @Transactional
    public ResponseEntity<String> updateUser(Long userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }

        User user = existingUser.get();

        // 更新用户的非空字段
        if (updatedUser.getUsername() != null) user.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword());
        if (updatedUser.getUserRole() != null) user.setUserRole(updatedUser.getUserRole());
        if (updatedUser.getPhone() != null) user.setPhone(updatedUser.getPhone());
        if (updatedUser.getAvatar() != null) user.setAvatar(updatedUser.getAvatar());
        if (updatedUser.getSex() != null) user.setSex(updatedUser.getSex());
        if (updatedUser.getToken() != null) user.setToken(updatedUser.getToken());

        userRepository.save(user);
        return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
    }

    // 删除用户（软删除）
    @Transactional
    public ResponseEntity<String> deleteUser(Long userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }

        User user = existingUser.get();
        user.setIsDelete(true); // 将 isDelete 设置为 true 以表示软删除
        userRepository.save(user);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }
}
