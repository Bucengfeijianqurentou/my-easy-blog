package com.gb.myeasyblog.dto;

import lombok.Data;

@Data
public class PostModifyReq {
    private Long id;
    private String title;
    private String content;
}
