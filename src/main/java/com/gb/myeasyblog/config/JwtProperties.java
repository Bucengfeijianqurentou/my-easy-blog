// JwtProperties.java
package com.gb.myeasyblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * JWT 签名密钥（必须至少 256 位 = 32 字节，建议 64 字符以上）
     */
    private String secret;

    /**
     * Token 过期时间（毫秒），默认 24 小时
     */
    private long expiration = 86400000; // 24 * 60 * 60 * 1000
}