package com.gb.myeasyblog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long postId;
    private Long userId;
    private String content;
    private Long parentId; // 对应 parent_id
    private LocalDateTime createdAt;
}
