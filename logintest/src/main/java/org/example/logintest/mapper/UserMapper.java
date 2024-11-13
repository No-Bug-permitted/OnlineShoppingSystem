package org.example.logintest.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.logintest.pojo.User;
import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select * from user where user_id=#{user_id}")
    User selectUserById(Integer user_id);
    @Select("select * from user where username=#{username}")
    User selectUserByName(String username);
    List<User> selectAll();

    @Insert("insert into user(username,password,email,user_role) values(#{username},#{password},#{email},#{user_role})")
    void register(String username, String password, String email, String user_role);
    int addUser(User user);
    int deleteUser(int user_id);
}
