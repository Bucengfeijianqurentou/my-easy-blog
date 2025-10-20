package com.gb.myeasyblog.dto;

import lombok.Data;

@Data
public class PostAddReqDTO {
    private String title;
    private String content;
    private Long userId;
}
