package com.gb.myeasyblog.exception;

public class BusinessException extends RuntimeException {
    private final Integer code; // 业务错误码

    public BusinessException(String message) {
        super(message);
        this.code = 400; // 默认业务错误码
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
