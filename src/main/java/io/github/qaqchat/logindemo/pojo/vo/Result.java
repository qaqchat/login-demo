package io.github.qaqchat.logindemo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "Success", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(500, message, null);
    }

    public static <T> Result<T> failure(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}

