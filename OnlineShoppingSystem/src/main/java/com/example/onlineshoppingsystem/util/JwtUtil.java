package com.example.onlineshoppingsystem.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret";  // 可以替换为更强的密钥

    public String generateToken(String username, String role, String phone) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("phone", phone);  // 将 phone 添加到 claims 中

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时有效期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 从 Token 中获取 phone
    public String getPhoneFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)  // 使用密钥进行签名验证
                .parseClaimsJws(token)
                .getBody();
        return claims.get("phone", String.class);  // 提取 phone 信息
    }

    // 验证 token 是否有效
    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    // 从 Token 中获取过期时间
    public Date getExpirationDateFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    // 从 Token 中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
