package com.gb.myeasyblog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentAddReqDTO {

    /**
     * 被评论的文章ID，必须为正整数
     */
    @NotNull(message = "文章ID不能为空")
    @Positive(message = "文章ID必须为正整数")
    private Long postId;

    /**
     * 评论内容，不能为空，限制长度
     */
    @NotBlank(message = "评论内容不能为空")
    @Size(min = 1, max = 500, message = "评论内容长度不能超过500个字符")
    private String content;

    /**
     * 父评论ID（可选）：
     * - 如果是直接评论文章，parentId 为 null 或 0（根据你的设计）
     * - 如果是回复某条评论，则必须为正整数
     *
     * 这里允许 null（表示一级评论），但如果传了，必须是正数
     */
    @Positive(message = "父评论ID必须为正整数")
    private Long parentId;
}