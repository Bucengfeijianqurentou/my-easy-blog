package com.gb.myeasyblog.exception;

import com.gb.myeasyblog.util.HttpStatusConstants;
import com.gb.myeasyblog.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    /**
     * 处理 @RequestBody 参数校验失败（POST/PUT JSON）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        log.warn("参数校验失败: {}", errors);

        return Result.of(400, "请求参数错误",errors);
    }


}
