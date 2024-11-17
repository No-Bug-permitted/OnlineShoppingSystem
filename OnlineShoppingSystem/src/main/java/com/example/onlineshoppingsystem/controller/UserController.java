package com.example.onlineshoppingsystem.controller;

import com.example.onlineshoppingsystem.entity.User;
import com.example.onlineshoppingsystem.service.UserService;
import com.example.onlineshoppingsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    // 注册
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            // 将前端传递的 account 映射到后端的 username 字段
            user.setUsername(user.getAccount());  // 使用 account 作为用户名

            // 将前端传递的 user_level 映射到后端的 user_role 字段
            user.setUserRole(user.getUserLevel());  // 设置用户角色

            // 检查手机号是否已存在
            Optional<User> existingUser = userService.findByPhone(user.getPhone());
            if (existingUser.isPresent()) {
                return new ResponseEntity<>("Phone number already in use", HttpStatus.BAD_REQUEST);
            }

            // 设置默认用户角色
            if (user.getUserRole() == null || user.getUserRole().isEmpty()) {
                user.setUserRole("consumer");  // 默认角色为消费者
            }

            // 注册操作
            ResponseEntity<String> response = userService.registerUser(user);  // 注册逻辑

            return response;
        } catch (Exception e) {
            // 捕获异常并打印日志，返回500错误和异常信息
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/debug")
    public void debug(@RequestBody Map<String, Object> payload) {
        System.out.println(payload);
    }

    // 登录
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody User user) {
        try {
            // 先处理传递的 user_level 字段，并映射到 role
            String role = user.getUser_level();  //获取用户登录的身份
            // 获取前端传递的手机号（account 字段），将 account 映射为 phone
            String phone = user.getAccount();  // 这里的 phone 是前端传过来的 account 字段
            // 检查用户是否存在
            Optional<User> existingUser = userService.findByPhone(phone);
            if (existingUser.isEmpty()) {
                return new ResponseEntity<>(new UserResponse("0", "User not found", null), HttpStatus.NOT_FOUND);
            }

            // 检查商家是否存在
            if (Objects.equals(role, "1") && !userService.isMerchantExist(phone)) {
                return new ResponseEntity<>(new UserResponse("0", "Merchant not found", null), HttpStatus.NOT_FOUND);
            }

            // 验证密码，直接比较明文密码
            if (!userService.checkPassword(user.getPassword(), existingUser.get().getPassword())) {
                return new ResponseEntity<>(new UserResponse("0", "Incorrect password", null), HttpStatus.UNAUTHORIZED);
            }

            // 登录成功后返回数据
            User loggedInUser = existingUser.get();

            // 生成新的 token，将 phone 作为参数传递
            String token = jwtUtil.generateToken(loggedInUser.getUsername(), loggedInUser.getUserRole(), loggedInUser.getPhone());

            // 更新用户的 token
            loggedInUser.setToken(token);
            userService.updateUser(loggedInUser.getUserId(), loggedInUser);  // 调用 updateUser 来保存更新的 token

            // 将 user_role 映射为 0 或 1
            String userRole = loggedInUser.getUserRole();
            String userLevel = "consumer".equals(userRole) ? "0" : ("merchant".equals(userRole) ? "1" : "-1");  // 默认 -1 表示其他角色

            // 创建并填充 UserResponse 对象
            UserResponse response = new UserResponse(
                    "1",  // code
                    "Operation successful",  // msg
                    new UserResponse.Result(
                            String.valueOf(loggedInUser.getUserId()),
                            loggedInUser.getPhone(),  // 返回 phone 字段
                            loggedInUser.getUsername(),  // 返回 username 字段（account）
                            userLevel,
                            loggedInUser.getToken() != null ? loggedInUser.getToken() : "",
                            loggedInUser.getAvatar(),
                            loggedInUser.getSex()
                    )
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            // 返回错误信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponse("0", "Login failed: " + e.getMessage(), null));
        }
    }

    // 内部类用于封装登录响应数据
    public static class UserResponse {
        private String code;
        private String msg;
        private Result result;

        // 构造函数
        public UserResponse(String code, String msg, Result result) {
            this.code = code;
            this.msg = msg;
            this.result = result;
        }

        // Getters and Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }


        // 内部 Result 类，包含登录用户的详细信息
        public static class Result {
            private String id;
            private String mobile;
            private String account;  // 返回值中的 account 对应 phone 字段
            private String user_level;
            private String token;
            private String avatar;
            private String gender;

            // 构造函数
            public Result(String id, String mobile, String account, String user_level, String token, String avatar, String gender) {
                this.id = id;
                this.mobile = mobile;
                this.account = account;  // 这里的 account 对应 phone 字段
                this.user_level = user_level;
                this.token = token;
                this.avatar = avatar;
                this.gender = gender;
            }

            // Getters and Setters
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getUser_level() {
                return user_level;
            }

            public void setUser_level(String user_level) {
                this.user_level = user_level;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }
        }
    }

}
