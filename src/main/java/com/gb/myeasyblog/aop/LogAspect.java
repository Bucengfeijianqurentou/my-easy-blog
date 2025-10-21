package com.gb.myeasyblog.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    // 1. 定义切点：com.gb.myeasyblog.controller 包下的所有类的所有方法
    @Pointcut("execution(* com.gb.myeasyblog.controller.*.*(..))")
    public void controllerLogPointcut() {}

    // 2. 定义环绕通知
    @Around("controllerLogPointcut()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 获取方法名和参数
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("==> [Controller] {}.{}() - Args: {}",
                className, methodName, Arrays.toString(args));

        // 3. 执行目标方法
        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        log.info("<== [Controller] {}.{}() - Result: {} - Time: {}ms",
                className, methodName, result, executionTime);

        // 4. 返回执行结果
        return result;
    }
}
