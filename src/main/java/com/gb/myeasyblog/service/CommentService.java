package com.gb.myeasyblog.service;

import com.gb.myeasyblog.dto.CommentAddReqDTO;
import com.gb.myeasyblog.util.Result;

public interface CommentService {
    Result add(CommentAddReqDTO commentAddReqDTO);
}
