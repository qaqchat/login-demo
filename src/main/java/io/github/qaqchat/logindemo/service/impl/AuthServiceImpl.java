package io.github.qaqchat.logindemo.service.impl;

import io.github.qaqchat.logindemo.mapper.AuthMapper;
import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.entity.User;
import io.github.qaqchat.logindemo.pojo.vo.UserTokenPayload;
import io.github.qaqchat.logindemo.pojo.vo.UserVO;
import io.github.qaqchat.logindemo.service.AuthService;
import io.github.qaqchat.logindemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserTokenPayload loginWithPassword(UserLoginDTO userLoginDTO) {
        String usernameOrEmail = userLoginDTO.getUsernameOrEmail();
        String password = userLoginDTO.getPassword();

        User user;
        if (usernameOrEmail.contains("@")) {
            user = authMapper.getByEmail(usernameOrEmail);
        } else  {
            user = authMapper.getByUsername(usernameOrEmail);
        }

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        String token = jwtUtil.generateToken(user.getUsername());

        UserVO userVO = new UserVO();
        userVO.setUsername(user.getUsername());
        userVO.setNickname(user.getNickname());
        userVO.setAvatar(user.getAvatar());
        userVO.setRegTime(user.getRegTime());

        UserTokenPayload userTokenPayload = new UserTokenPayload();
        userTokenPayload.setToken(token);
        userTokenPayload.setPayload(userVO);

        return userTokenPayload;
    }

    @Override
    public UserTokenPayload register(UserRegisterDTO userRegisterDTO) {
        // 1. 查询用户名是否存在

        // 2. 查询邮箱是否存在

        // 3. 查询验证码是否合法

        // 4. 补充用户其他字段，插入数据库

        // 5. 返回VO

        return null;
    }
}
