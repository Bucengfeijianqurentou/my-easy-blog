package com.gb.myeasyblog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostModifyReq {
    @NotNull(message = "文章ID不能为空")
    @Positive(message = "文章ID必须为正整数")
    private Long id;

    @Size(min = 1, max = 100, message = "标题长度必须在1到100个字符之间")
    private String title;

    @Size(min = 1, max = 10000, message = "内容长度不能超过10000个字符")
    private String content;
}
