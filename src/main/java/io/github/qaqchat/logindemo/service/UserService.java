package io.github.qaqchat.logindemo.service;

import io.github.qaqchat.logindemo.pojo.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserVO getUserInfoById(int id);
}
