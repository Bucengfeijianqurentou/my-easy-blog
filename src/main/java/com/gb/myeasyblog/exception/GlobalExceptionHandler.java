package com.gb.myeasyblog.exception;

import com.gb.myeasyblog.util.HttpStatusConstants;
import com.gb.myeasyblog.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.warn("业务异常：code={}, message={}", e.getCode(), e.getMessage());
        return Result.error(e.getCode(),e.getMessage());
    }

    /**
     * 处理其他未预期的异常（可选）
     */
    @ExceptionHandler(Exception.class)
    public Result handleUnexpectedException(Exception e) {
        log.error("系统异常：{}", e.getMessage());
        return Result.error(HttpStatusConstants.INTERNAL_ERROR,"系统内部错误，请稍后再试："+e.getMessage());
    }

    //todo 处理参数校验异常

}
