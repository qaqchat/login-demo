package io.github.qaqchat.logindemo.mapper;

import io.github.qaqchat.logindemo.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {
    @Select("SELECT * FROM users WHERE email = #{email}")
    User getByEmail(String email);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getByUsername(String username);
}
