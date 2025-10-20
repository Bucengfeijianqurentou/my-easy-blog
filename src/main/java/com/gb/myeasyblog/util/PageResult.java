package com.gb.myeasyblog.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 分页数据统一封装类
 * @param <T>
 */
@Data
public class PageResult<T> {

    /**
     * 当前页码 (从 1 开始)
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 当前页的数据列表
     */
    private List<T> list;

    // 私有化构造函数，强制使用静态工厂方法
    private PageResult() {
    }

    // --- 静态工厂方法 (核心) ---

    /**
     * [核心] 从 PageHelper 的 PageInfo 转换
     * @param pageInfo PageHelper 的分页结果
     * @return 统一的分页结果
     */
    public static <T> PageResult<T> of(PageInfo<T> pageInfo) {
        PageResult<T> result = new PageResult<>();
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setTotalPages(pageInfo.getPages());
        result.setList(pageInfo.getList());
        return result;
    }

}
