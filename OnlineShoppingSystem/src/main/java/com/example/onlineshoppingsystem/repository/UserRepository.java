package com.example.onlineshoppingsystem.repository;

import com.example.onlineshoppingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据 phone 查找用户
    Optional<User> findByPhone(String phone);

    // 判断 phone 是否已存在
    boolean existsByPhone(String phone);
}
