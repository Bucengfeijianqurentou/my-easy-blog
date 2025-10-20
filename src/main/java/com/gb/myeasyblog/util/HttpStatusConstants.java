package com.gb.myeasyblog.util;

/**
 * HTTP状态码常量定义
 */
public class HttpStatusConstants {

    /**
     * 请求成功
     * 表示服务器成功处理了请求
     */
    public static final int SUCCESS = 200;

    /**
     * 客户端请求错误
     * 表示服务器无法理解客户端的请求，通常是请求参数有误
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     * 表示请求要求用户的身份认证，需要登录
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 禁止访问
     * 表示服务器理解请求但拒绝执行，通常是权限不足
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源未找到
     * 表示服务器无法根据客户端的请求找到对应的资源
     */
    public static final int NOT_FOUND = 404;

    /**
     * 服务器内部错误
     * 表示服务器遇到了一个意外的情况，导致无法完成请求
     */
    public static final int INTERNAL_ERROR = 500;


}

