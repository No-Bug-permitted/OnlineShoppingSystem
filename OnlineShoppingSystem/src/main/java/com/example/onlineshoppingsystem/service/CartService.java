package com.example.onlineshoppingsystem.service;

import com.example.onlineshoppingsystem.entity.Cart;
import com.example.onlineshoppingsystem.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // 清空本地购物车
    @Transactional
    public void clearLocalCart(Long userId) {
        // 通过 userId 删除该用户的所有购物车项
        cartRepository.deleteByUserId(userId);
    }
}
