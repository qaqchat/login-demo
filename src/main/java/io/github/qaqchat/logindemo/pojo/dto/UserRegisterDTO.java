package io.github.qaqchat.logindemo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    @NotNull
    @Schema(description = "用户名")
    private String username;
    @NotNull
    @Schema(description = "邮箱")
    private String email;
    @NotNull
    @Schema(description = "密码")
    private String password;
    @NotNull
    @Schema(description = "验证码")
    private String code;
}
