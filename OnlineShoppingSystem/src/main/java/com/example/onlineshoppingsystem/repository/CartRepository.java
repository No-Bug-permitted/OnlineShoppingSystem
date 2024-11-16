package com.example.onlineshoppingsystem.repository;

import com.example.onlineshoppingsystem.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 查找用户购物车中的某个商品
    Cart findByUserIdAndGoodsId(Long userId, Long goodsId);

    // 根据 userId 删除用户的所有购物车项
    void deleteByUserId(Long userId);
}
