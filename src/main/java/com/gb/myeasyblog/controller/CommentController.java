package com.gb.myeasyblog.controller;

import com.gb.myeasyblog.dto.CommentAddReqDTO;
import com.gb.myeasyblog.service.CommentService;
import com.gb.myeasyblog.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    /**
     * 添加评论接口
     *
     * @param commentAddReqDTO 评论添加请求数据传输对象，包含评论内容、用户信息等
     * @return 返回添加结果，包含操作状态和相关数据
     */
    @PostMapping("/add")
    public Result add(@RequestBody CommentAddReqDTO commentAddReqDTO) {
        return commentService.add(commentAddReqDTO);
    }


}
