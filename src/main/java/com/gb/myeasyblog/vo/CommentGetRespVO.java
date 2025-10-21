package com.gb.myeasyblog.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommentGetRespVO {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;
    private Long parentId;
    private LocalDateTime createdAt;
    private List<CommentGetRespVO> children = new ArrayList<>(); // 子评论列表
}
