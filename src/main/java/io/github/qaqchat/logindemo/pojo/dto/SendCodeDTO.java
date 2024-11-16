package io.github.qaqchat.logindemo.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendCodeDTO {
    @NotNull
    @Schema(description = "邮箱")
    private String email;
    @NotNull
    @Schema(description = "目的")
    private String purpose;
}
