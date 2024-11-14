package io.github.qaqchat.logindemo.controller;

import io.github.qaqchat.logindemo.pojo.dto.SendCodeDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.vo.Result;
import io.github.qaqchat.logindemo.pojo.vo.UserTokenPayload;
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
    public Result<UserTokenPayload> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserTokenPayload userTokenPayload = authService.loginWithPassword(userLoginDTO);
        if (userTokenPayload == null) {
            return Result.failure("not exits");
        }
        return Result.success(userTokenPayload);
    }

    @PostMapping("/login/wechat")
    public Result wechatLogin() {
        // TODO
        return Result.failure("not support wechat login");
    }

    @PostMapping("register")
    public Result<UserTokenPayload> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserTokenPayload userTokenPayload = authService.register(userRegisterDTO);
        if (userTokenPayload == null) {
            return Result.failure("error");
        }
        return Result.success(userTokenPayload);
    }

    @PostMapping("/send-code")
    public Result<Object> sendCode(@RequestBody SendCodeDTO sendCodeDTO) {

        return null;
    }

}
