package com.gb.myeasyblog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageReqDTO {
    /**
     * 当前页码，默认为 1（第一页）
     */
    @Min(value = 1, message = "页码不能小于1")
    private int pageNum = 1;

    /**
     * 每页大小，默认为 10 条
     * 建议限制最大值（如 100），防止恶意请求导致数据库压力过大
     */
    @Min(value = 1, message = "每页大小不能小于1")
    @Max(value = 100, message = "每页大小不能超过100")
    private int pageSize = 10;
}
