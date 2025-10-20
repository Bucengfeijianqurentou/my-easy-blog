package com.gb.myeasyblog.mapper;

import com.gb.myeasyblog.dto.PostModifyReq;
import com.gb.myeasyblog.entity.Post;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    int insert(Post post);

    Page<Post> selectAllPosts();

    Page<Post> selectByUserId(Long userId);

    Post selectById(Integer id);

    int update(Post post);

    int deleteById(Integer id);
}
