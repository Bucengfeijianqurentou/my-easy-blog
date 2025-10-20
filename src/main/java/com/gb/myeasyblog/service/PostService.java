package com.gb.myeasyblog.service;

import com.gb.myeasyblog.dto.PageReqDTO;
import com.gb.myeasyblog.dto.PostAddReqDTO;
import com.gb.myeasyblog.dto.PostModifyReq;
import com.gb.myeasyblog.entity.Post;
import com.gb.myeasyblog.util.PageResult;
import com.gb.myeasyblog.util.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostService {


    Result<Post> save(PostAddReqDTO postAddReqDTO);

    PageResult<Post> getAll(PageReqDTO pageReqDTO);

    Result<Post> getById(Integer id);

    Result<Post> modify(PostModifyReq postModifyReq);

    Result removeById(Integer id);

    PageResult<Post> getPostsByUserId(PageReqDTO pageReqDTO);
}
