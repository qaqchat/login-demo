package io.github.qaqchat.logindemo.controller;

import io.github.qaqchat.logindemo.pojo.dto.SendCodeDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.vo.Result;
import io.github.qaqchat.logindemo.pojo.vo.SendCodeResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserLoginResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserRegisterResponseVO;
import io.github.qaqchat.logindemo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login/password")
    public Result<UserLoginResponseVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginResponseVO userLoginResponseVO = authService.loginWithPassword(userLoginDTO);
        if (userLoginResponseVO == null) {
            return Result.failure("not exits");
        }
        return Result.success(userLoginResponseVO);
    }

    @PostMapping("/login/wechat")
    public Result wechatLogin() {
        // TODO
        return Result.failure("not support wechat login");
    }

    @PostMapping("register")
    public Result<UserRegisterResponseVO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserRegisterResponseVO userRegisterResponseVO= authService.register(userRegisterDTO);
        if (userRegisterResponseVO == null) {
            return Result.failure("error");
        }
        return Result.success(userRegisterResponseVO);
    }

    @PostMapping("/send-code")
    public Result<SendCodeResponseVO> sendCode(@RequestBody SendCodeDTO sendCodeDTO) {
        SendCodeResponseVO sendCodeResponseVO = authService.sendCode(sendCodeDTO);
        return Result.success(sendCodeResponseVO);
    }

}
