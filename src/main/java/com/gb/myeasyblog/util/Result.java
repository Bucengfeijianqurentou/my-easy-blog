package com.gb.myeasyblog.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一 API 响应结果封装类
 *
 * @param <T> 返回数据的类型
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;    // 状态码（200 成功，其他失败）
    private String message;  // 提示信息
    private T data;          // 返回数据

    // 私有构造器，防止外部直接 new
    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功：无数据
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功：带数据
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功：自定义消息 + 数据
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    // 失败：仅消息
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // 失败：自定义状态码 + 消息
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    // 自定义构建（高级用法）
    public static <T> Result<T> of(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }
}