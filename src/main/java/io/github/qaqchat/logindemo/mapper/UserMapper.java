package io.github.qaqchat.logindemo.mapper;

import io.github.qaqchat.logindemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE email = #{email}")
    User getByEmail(String email);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getByUsername(String username);
}
