package org.example.logintest.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.logintest.pojo.User;
import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select user_id,username,password,user_role,phone,avatar,sex,token from user where phone=#{phone}")
    User queryUserByPhone(String phone);

    @Insert("insert into user(username,password,user_role,phone) values(#{username},#{password},#{user_role},#{phone})")
    void register(String username, String password, String user_role, String phone);

    int addUser(User user);
    int deleteUser(int user_id);
}
