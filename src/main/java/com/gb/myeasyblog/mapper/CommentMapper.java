package com.gb.myeasyblog.mapper;

import com.gb.myeasyblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);

    List<Comment> selectAllComment();

    /**
     * 根据文章ID查询评论列表
     *
     * @param postId 文章ID
     * @return 该文章下的所有评论
     */
    List<Comment> selectByPostId(Long postId);
}
