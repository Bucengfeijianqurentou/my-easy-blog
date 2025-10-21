package com.gb.myeasyblog.service;

import com.gb.myeasyblog.dto.CommentAddReqDTO;
import com.gb.myeasyblog.entity.Comment;
import com.gb.myeasyblog.util.Result;
import com.gb.myeasyblog.vo.CommentGetRespVO;

import java.util.List;

public interface CommentService {
    Result add(CommentAddReqDTO commentAddReqDTO);

    Result<List<Comment>> getAllComment();

    /**
     * 根据文章ID获取评论树形结构
     * @param postId 文章ID
     * @return Result<List<CommentVO>> 树形结构的评论列表
     */
    Result<List<CommentGetRespVO>> getCommentsByPostId(Long postId);

}
