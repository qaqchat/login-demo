package io.github.qaqchat.logindemo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String id;
    private String username;
    private String nickname;
    private String avatar;
    private LocalDateTime regTime;
}
