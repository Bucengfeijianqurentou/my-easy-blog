package com.gb.myeasyblog.interceptor;

import com.gb.myeasyblog.util.JwtUtil;
import com.gb.myeasyblog.util.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果处理器不是HandlerMethod类型，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        try {
            // 从请求头中获取token并进行验证
            String token = request.getHeader(TOKEN_HEADER);
            if (token != null && jwtUtil.validateToken(token)) {
                // token验证通过，设置用户上下文信息
                Long userId = jwtUtil.getUserIdFromToken(token);
                UserContext.setUserId(userId);
                log.info("用户已登录，当前用户ID是：{}",userId);
                return true;
            }
            // token验证失败，拒绝访问
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.warn("校验不通通过！");
            return false;
        } catch (Exception e) {
            // token校验异常处理
            log.warn("校验不通过");
            log.error("JWT校验错误：{}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    private static final String TOKEN_HEADER = "token";


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清理ThreadLocal中的用户上下文信息，防止内存泄漏
        UserContext.clear();
    }

}
