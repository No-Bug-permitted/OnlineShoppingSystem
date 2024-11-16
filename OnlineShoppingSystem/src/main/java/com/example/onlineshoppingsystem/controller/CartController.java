package com.example.onlineshoppingsystem.controller;

import com.example.onlineshoppingsystem.entity.User;
import com.example.onlineshoppingsystem.service.CartService;
import com.example.onlineshoppingsystem.service.UserService;
import com.example.onlineshoppingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/member/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtUtil jwtUtil;  // 注入 JwtUtil，用于生成新的 token

    @Autowired
    private UserService userService;  // 用于更新用户信息的服务

    // 登录后清空本地购物车 API
    @PostMapping("/merge")
    public ResponseEntity<Map<String, Object>> clearCart(@RequestHeader("Authorization") String authorization) {
        try {
            // 获取 Authorization 头部中的 Bearer token
            String token = extractToken(authorization);

            // 获取手机号（phone）从 token
            String phone = jwtUtil.getPhoneFromToken(token);  // 使用自定义的方法从 token 获取 phone

            // 根据 phone 获取用户
            User loggedInUser = userService.findByPhone(phone).orElse(null);  // 根据手机号查找用户
            if (loggedInUser == null) {
                // 调试输出
                System.out.println("token: " + token);
                System.out.println("Parsed phone from token: " + phone);
                System.out.println("Found user: " + loggedInUser);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorResponse("token校验失败"));
            }


            // 调用服务层清空本地购物车
            cartService.clearLocalCart(loggedInUser.getUserId());

            // 返回成功响应
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("code", "1");
            response.put("msg", "操作成功");
            response.put("result", null);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 返回通用的错误响应
            Map<String, Object> response = createErrorResponse("操作失败");
            response.put("result", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 提取 Bearer token
    private String extractToken(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization header is missing or malformed");
        }
        return authorization.substring(7);  // 去掉 "Bearer " 前缀，返回纯 token
    }

    // 生成错误响应
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);  // 错误消息
        response.put("code", "10019");     // 错误代码
        return response;
    }

}
