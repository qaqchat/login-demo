package io.github.qaqchat.logindemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String username;
    private String nickname;
    private String avatar;
    private LocalDateTime regTime;
}
