package com.gb.myeasyblog.mapper;

import com.gb.myeasyblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
}
