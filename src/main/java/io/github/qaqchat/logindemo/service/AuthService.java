package io.github.qaqchat.logindemo.service;

import io.github.qaqchat.logindemo.pojo.dto.SendCodeDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.vo.SendCodeResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserLoginResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserRegisterResponseVO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserLoginResponseVO loginWithPassword(UserLoginDTO userLoginDTO);

    UserRegisterResponseVO register(UserRegisterDTO userRegisterDTO);

    SendCodeResponseVO sendCode(SendCodeDTO sendCodeDTO);
}
