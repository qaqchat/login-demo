package io.github.qaqchat.logindemo.mapper;

import io.github.qaqchat.logindemo.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {
    @Select("SELECT * FROM users WHERE email = #{email}")
    User getByEmail(String email);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getByUsername(String username);

    @Select("SELECT COUNT(*) FROM users WHERE email = #{email}")
    Integer getEmailCount(String email);

    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    Integer getUsernameCount(String username);

    @Insert("INSERT INTO users (username, email, password, nickname, avatar, regTime) VALUES (#{username}, #{email}, #{password}, #{nickname}, #{avatar}, #{regTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);
}
