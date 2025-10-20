package com.gb.myeasyblog.dto;

import lombok.Data;

@Data
public class CommentAddReqDTO {
    private Long postId;
    private String content;
    private Long parentId; // 如果是回复别人的评论，就传这个ID
}
