package io.github.qaqchat.logindemo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseVO {
    private Boolean success;
    private String token;
    private UserVO payload;
}
