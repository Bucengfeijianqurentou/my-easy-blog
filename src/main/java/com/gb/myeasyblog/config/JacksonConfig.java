package com.gb.myeasyblog.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            // 1. 设置全局时区
            builder.timeZone(TimeZone.getTimeZone("GMT+8"));

            // 2. 针对 java.util.Date 的全局格式化 (保持 application.properties 的行为)
            builder.simpleDateFormat(DATETIME_FORMAT);

            // 3. 【核心】针对 java.time.LocalDateTime (JSR310) 的全局格式化
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(
                    LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
            );

            builder.modules(javaTimeModule);
        };
    }
}
