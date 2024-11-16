package io.github.qaqchat.logindemo.controller;

import io.github.qaqchat.logindemo.pojo.dto.SendCodeDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.vo.Result;
import io.github.qaqchat.logindemo.pojo.vo.SendCodeResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserLoginResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserRegisterResponseVO;
import io.github.qaqchat.logindemo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "权限")
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "密码登录")
    @PostMapping("/login/password")
    public Result<UserLoginResponseVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginResponseVO userLoginResponseVO = authService.loginWithPassword(userLoginDTO);
        if (userLoginResponseVO == null) {
            return Result.failure("not exits");
        }
        return Result.success(userLoginResponseVO);
    }

    @Operation(summary = "微信登录")
    @PostMapping("/login/wechat")
    public Result wechatLogin() {
        // TODO
        return Result.failure("not support wechat login");
    }

    @Operation(summary = "注册")
    @PostMapping("register")
    public Result<UserRegisterResponseVO> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserRegisterResponseVO userRegisterResponseVO= authService.register(userRegisterDTO);
        if (userRegisterResponseVO == null) {
            return Result.failure("error");
        }
        return Result.success(userRegisterResponseVO);
    }

    @Operation(summary = "发送验证码")
    @PostMapping("/send-code")
    public Result<SendCodeResponseVO> sendCode(@RequestBody SendCodeDTO sendCodeDTO) {
        SendCodeResponseVO sendCodeResponseVO = authService.sendCode(sendCodeDTO);
        return Result.success(sendCodeResponseVO);
    }

}
