package io.github.qaqchat.logindemo.service.impl;

import io.github.qaqchat.logindemo.mapper.AuthMapper;
import io.github.qaqchat.logindemo.pojo.dto.SendCodeDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserLoginDTO;
import io.github.qaqchat.logindemo.pojo.dto.UserRegisterDTO;
import io.github.qaqchat.logindemo.pojo.entity.User;
import io.github.qaqchat.logindemo.pojo.vo.SendCodeResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserLoginResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserRegisterResponseVO;
import io.github.qaqchat.logindemo.pojo.vo.UserVO;
import io.github.qaqchat.logindemo.service.AuthService;
import io.github.qaqchat.logindemo.util.EmailSender;
import io.github.qaqchat.logindemo.util.JwtUtil;
import io.github.qaqchat.logindemo.util.VerificationCodeGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private VerificationCodeGenerator verificationCodeGenerator;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserLoginResponseVO loginWithPassword(UserLoginDTO userLoginDTO) {
        String usernameOrEmail = userLoginDTO.getUsernameOrEmail();
        String password = userLoginDTO.getPassword();

        User user = getUserByUsernameOrEmail(usernameOrEmail);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        String token = jwtUtil.generateToken(user.getUsername());

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setId(user.getId().toString());

        UserLoginResponseVO userLoginResponseVO = new UserLoginResponseVO();
        userLoginResponseVO.setToken(token);
        userLoginResponseVO.setPayload(userVO);

        return userLoginResponseVO;
    }

    @Override
    public UserRegisterResponseVO register(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        String email = userRegisterDTO.getEmail();

        // 1. 查询用户名或邮箱是否存在
        if (authMapper.getUsernameCount(username) > 0 || authMapper.getEmailCount(email) > 0) {
            return null;
        }

        // 2. 查询验证码是否合法
        String key = email + ":registration";
        String code = (String) redisTemplate.opsForValue().get(key);
        if (code == null || !code.equals(userRegisterDTO.getCode())) {
            return null;
        }

        // 3. 补充用户其他字段，插入数据库
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(userRegisterDTO.getPassword());
        user.setNickname(username);
        user.setAvatar("example.com");
        LocalDateTime regTime = LocalDateTime.now();
        user.setRegTime(regTime);
        authMapper.insert(user);

        // 4. 返回VO
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setId(user.getId().toString());

        UserRegisterResponseVO userRegisterResponseVO = new UserRegisterResponseVO();
        String token = jwtUtil.generateToken(user.getUsername());
        userRegisterResponseVO.setSuccess(Boolean.TRUE);
        userRegisterResponseVO.setToken(token);
        userRegisterResponseVO.setPayload(userVO);

        return userRegisterResponseVO;
    }

    @Override
    public SendCodeResponseVO sendCode(SendCodeDTO sendCodeDTO) {
        SendCodeResponseVO sendCodeResponseVO = new SendCodeResponseVO();
        String email = sendCodeDTO.getEmail();
        String purpose = sendCodeDTO.getPurpose();

        // 1. 生成验证码
        String code = verificationCodeGenerator.generateCode();
        // 2. 存到 redis
        String key = email + ":" + purpose;
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        // 3. 发送邮件
        emailSender.sendVerificationCode(email, purpose, code);
        // 4. 返回VO
        sendCodeResponseVO.setSuccess(Boolean.TRUE);
        return sendCodeResponseVO;
    }

    private User getUserByUsernameOrEmail(String usernameOrEmail) {
        if (usernameOrEmail.contains("@")) {
            return authMapper.getByEmail(usernameOrEmail);
        } else {
            return authMapper.getByUsername(usernameOrEmail);
        }
    }
}
