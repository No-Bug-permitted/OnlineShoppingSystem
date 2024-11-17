package com.example.onlineshoppingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javax.persistence.Transient;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phone") // 确保 phone 字段唯一
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;

    @Column(nullable = false)
    private String userRole; // "CONSUMER", "MERCHANT", "ADMIN"

    @Column(nullable = false, unique = true)
    private String phone; // 数据库唯一标识，与前端的 'account' 字段映射

    private String avatar; // 用户头像
    private String sex;    // 性别

    @Transient
    private transient String account; // 临时字段，不存储在数据库中

    @Transient
    private  String user_level; // 临时字段，不存储在数据库中

    @Column(nullable = false)
    private String token = "";  // 默认 token 值，避免为 null

    @Column(name = "isdelete")
    private Boolean isDelete = false; // 逻辑删除标记

    // Getters 和 Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    // 对应 user_level 字段的 getter 和 setter
    public String getUserLevel() {
        return userRole;  // 映射到 user_role 字段
    }

    public String getUser_level() {
        return user_level;  // 映射到 user_role 字段
    }

    public void setUserLevel(String userLevel) {
        this.userRole = userLevel;  // 设置 user_role 字段
    }
}
