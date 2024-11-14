package io.github.qaqchat.logindemo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenPayload {
    private String token;
    private UserVO payload;
}
