package com.gb.myeasyblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 允许的前端源（生产环境建议明确指定，不要用 *）
        config.setAllowedOriginPatterns(List.of("*")); // Spring Boot 2.4+ 推荐用 allowedOriginPatterns 代替 allowedOrigins
        // config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "https://yourdomain.com"));

        // 允许携带凭证（如 cookies），若设为 true，则 allowedOriginPatterns 不能为 "*"
        config.setAllowCredentials(false);

        // 允许的 HTTP 方法
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

        // 允许的请求头
        config.setAllowedHeaders(Arrays.asList("*"));

        // 暴露给前端的响应头（可选）
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        // 预检请求缓存时间（秒）
        config.setMaxAge(3600L);

        // 将 CORS 配置应用到所有路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}