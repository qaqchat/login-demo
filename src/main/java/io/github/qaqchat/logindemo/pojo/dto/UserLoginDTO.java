package io.github.qaqchat.logindemo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotNull
    @Schema(description = "用户名或邮箱")
    private String usernameOrEmail;
    @NotNull
    @Schema(description = "密码")
    private String password;
}
