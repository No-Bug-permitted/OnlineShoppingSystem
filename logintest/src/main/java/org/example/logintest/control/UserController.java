package org.example.logintest.control;

import jakarta.validation.constraints.Pattern;
import org.example.logintest.pojo.Result;
import org.example.logintest.pojo.User;
import org.example.logintest.service.UserService;
import org.example.logintest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Validated//参数校验
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp ="^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$" ) String phone, String username,
                           @Pattern(regexp ="^[0-9]{6,18}$" ) String password){
        System.out.println(phone+username+password);
            //判断标志，选择注册用户身份为消费者或商家
            User u = userService.queryUserByPhone(phone);
            if (u == null) {
                userService.register(username, password, "customer", phone);
                return Result.success("注册成功");
            } else {
                return Result.error("用户已存在");
            }
    }

    /*@RequestMapping("/login")
    public Result login(@Pattern(regexp ="/^1[3,4,5,6,7,8,9][0-9]{9}$/" )String phone,
                        @Pattern(regexp ="^\\${5,16}$" )String password){
        User u=userService.login(phone,password);
        if(u!=null){
            return Result.success(u);
        }else{
            return Result.error("手机号或密码错误");
        }
    }*/
    //@PostMapping("/login")
    @RequestMapping ("/login")
    public Result login(@Pattern(regexp ="^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$" ) String phone,
                        @Pattern(regexp ="^[0-9]{6,18}$" ) String password){
        User u=userService.queryUserByPhone(phone);
        if(u==null){
            return Result.error("用户不存在");
        }

       if(u.getPassword().equals(password)){//明文传输，后续需加密
           Map<String, Object> claims=new HashMap<>();
           claims.put("id",u.getUser_id());
           claims.put("phone",u.getPhone());
           String token=JwtUtil.generateToken(claims);
           return Result.success(token);
       }else{
           return Result.error("密码错误");
       }
    }
}
