package io.github.qaqchat.logindemo.controller;
import io.github.qaqchat.logindemo.pojo.Result;

import io.github.qaqchat.logindemo.pojo.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.UserTokenPayload;
import io.github.qaqchat.logindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<UserTokenPayload> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserTokenPayload userTokenPayload = userService.loginWithUsernameOrEmail(userLoginDTO);
        if (userTokenPayload == null) {
            return Result.failure("not exits");
        }
        return Result.success(userTokenPayload);
    }

    @PostMapping("/login/emailVer")
    Result emailVerLogin() {
        return Result.failure("not support mail login");
    }

    @PostMapping("/login/wechat")
    Result wechatLogin() {
        return Result.failure("not support wechat login");
    }

}
