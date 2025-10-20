package com.gb.myeasyblog.dto;

import lombok.Data;

@Data
public class PageReqDTO {
    /**
     * 当前页码，默认为 1（第一页）
     */
    private int pageNum = 1;

    /**
     * 每页大小，默认为 10 条
     * 建议限制最大值（如 100），防止恶意请求导致数据库压力过大
     */
    private int pageSize = 10;
}
