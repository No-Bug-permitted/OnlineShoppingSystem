package com.example.onlineshoppingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.onlineshoppingsystem.entity")  // 显式指定扫描实体类的包
public class OnlineShoppingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingSystemApplication.class, args);
	}
}
