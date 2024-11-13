package io.github.qaqchat.logindemo.service;

import io.github.qaqchat.logindemo.pojo.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.UserTokenPayload;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserTokenPayload loginWithUsernameOrEmail(UserLoginDTO userLoginDTO);
}
