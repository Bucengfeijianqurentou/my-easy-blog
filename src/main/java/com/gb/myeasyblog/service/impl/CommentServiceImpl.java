package com.gb.myeasyblog.service.impl;

import com.gb.myeasyblog.dto.CommentAddReqDTO;
import com.gb.myeasyblog.entity.Comment;
import com.gb.myeasyblog.mapper.CommentMapper;
import com.gb.myeasyblog.service.CommentService;
import com.gb.myeasyblog.util.HttpStatusConstants;
import com.gb.myeasyblog.util.Result;
import com.gb.myeasyblog.util.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    /**
     * 添加评论
     *
     * @param commentAddReqDTO 评论添加请求数据传输对象，包含评论内容、关联的文章或资源ID等信息
     * @return Result 返回操作结果，成功时返回"添加成功"，失败时返回相应的错误信息和状态码
     */
    @Override
    public Result add(CommentAddReqDTO commentAddReqDTO) {
        // 参数校验
        if (commentAddReqDTO == null) {
            return Result.error(HttpStatusConstants.BAD_REQUEST, "请求参数不能为空");
        }

        // 获取当前用户ID
        Long userId = UserContext.getUserId();
        if (userId == null) {
            return Result.error(HttpStatusConstants.UNAUTHORIZED, "用户未登录");
        }

        // 构造评论实体对象并设置基础属性
        Comment comment = new Comment();
        try {
            BeanUtils.copyProperties(commentAddReqDTO, comment);
        } catch (Exception e) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "参数转换失败");
        }
        comment.setUserId(userId);
        comment.setCreatedAt(LocalDateTime.now());

        // 执行数据库插入操作
        try {
            int count = commentMapper.insert(comment);
            if (count != 1) {
                return Result.error(HttpStatusConstants.INTERNAL_ERROR, "服务器内部错误");
            }
        } catch (Exception e) {
            return Result.error(HttpStatusConstants.INTERNAL_ERROR, "数据库操作失败");
        }

        return Result.success("添加成功");
    }


}
