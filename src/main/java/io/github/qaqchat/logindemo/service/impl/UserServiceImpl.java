package io.github.qaqchat.logindemo.service.impl;

import io.github.qaqchat.logindemo.mapper.UserMapper;
import io.github.qaqchat.logindemo.pojo.User;
import io.github.qaqchat.logindemo.pojo.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.UserTokenPayload;
import io.github.qaqchat.logindemo.pojo.UserVO;
import io.github.qaqchat.logindemo.service.UserService;
import io.github.qaqchat.logindemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserTokenPayload loginWithUsernameOrEmail(UserLoginDTO userLoginDTO) {
        String usernameOrEmail = userLoginDTO.getUsernameOrEmail();
        String password = userLoginDTO.getPassword();

        User user;
        if (usernameOrEmail.contains("@")) {
            user = userMapper.getByEmail(usernameOrEmail);
        } else  {
            user = userMapper.getByUsername(usernameOrEmail);
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
}
