package com.gb.myeasyblog.controller;

import com.gb.myeasyblog.dto.CommentAddReqDTO;
import com.gb.myeasyblog.entity.Comment;
import com.gb.myeasyblog.service.CommentService;
import com.gb.myeasyblog.util.Result;
import com.gb.myeasyblog.vo.CommentGetRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    /**
     * 添加评论接口
     *
     * @param commentAddReqDTO 评论添加请求数据传输对象，包含评论内容、用户信息等
     * @return 返回添加结果，包含操作状态和相关数据
     */
    @PostMapping
    public Result add(@RequestBody CommentAddReqDTO commentAddReqDTO) {
        return commentService.add(commentAddReqDTO);
    }


    /**
     * 获取所有评论信息
     *
     * @return 返回包含所有评论信息的结果对象
     */
    @GetMapping
    public Result<List<Comment>> getAllComment() {
        return commentService.getAllComment();
    }


    /**
     * 根据文章ID获取评论树形结构
     * @param postId 文章ID
     * @return Result<List<CommentVO>> 树形结构的评论列表
     */
    @GetMapping("/getTreeComment/{id}")
    Result<List<CommentGetRespVO>> getCommentsByPostId(@PathVariable("id") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }



}
