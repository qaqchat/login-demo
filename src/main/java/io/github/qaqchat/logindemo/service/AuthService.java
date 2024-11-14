package io.github.qaqchat.logindemo.service;

import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.vo.UserTokenPayload;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserTokenPayload loginWithPassword(UserLoginDTO userLoginDTO);

    UserTokenPayload register(UserRegisterDTO userRegisterDTO);
}
