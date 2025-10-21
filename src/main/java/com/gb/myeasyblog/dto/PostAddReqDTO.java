package com.gb.myeasyblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostAddReqDTO {
    @NotBlank(message = "标题不能为空")
    @Size(min = 1, max = 100, message = "标题长度必须在1到100个字符之间")
    private String title;

    @NotBlank(message = "内容不能为空")
    @Size(min = 1, max = 10000, message = "内容长度不能超过10000个字符")
    private String content;

    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
