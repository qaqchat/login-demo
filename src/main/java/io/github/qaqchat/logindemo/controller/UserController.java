package io.github.qaqchat.logindemo.controller;

import io.github.qaqchat.logindemo.pojo.vo.Result;
import io.github.qaqchat.logindemo.pojo.vo.UserVO;
import io.github.qaqchat.logindemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户")
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "查询用户信息")
    @GetMapping("/id={id}")
    public Result<UserVO> getUserInfo(@PathVariable("id") Integer id) {
        UserVO userVO = userService.getUserInfoById(id);
        if (userVO == null) {
            return Result.failure("error");
        }
        return Result.success(userVO);
    }
}
