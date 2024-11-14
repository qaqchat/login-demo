package io.github.qaqchat.logindemo.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendCodeDTO {
    private String email;
    private String purpose;
}
