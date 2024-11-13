package org.example.logintest.control;

import org.example.logintest.pojo.Result;
import org.example.logintest.pojo.User;
import org.example.logintest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result login(String username, String password){
        User u=userService.selectUserByName(username);
        if(u==null){
            userService.register(username,password,null,null);
            return Result.success("注册成功");
        }else{
            return Result.error("用户已存在");
        }
    }

    @RequestMapping("/selectById")
    public User selectUserById(Integer id){
        return userService.selectUserById(id);
    }
}
