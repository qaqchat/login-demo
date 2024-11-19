package io.github.qaqchat.logindemo.service.impl;

import io.github.qaqchat.logindemo.mapper.UserMapper;
import io.github.qaqchat.logindemo.pojo.entity.User;
import io.github.qaqchat.logindemo.pojo.vo.UserVO;
import io.github.qaqchat.logindemo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO getUserInfoById(int id) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setId(user.getId().toString());
        return userVO;
    }
}
