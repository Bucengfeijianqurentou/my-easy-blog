package com.gb.myeasyblog.mapper;

import com.gb.myeasyblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User user);
    User selectByUsername(String username);
    User selectById(Long id);
}
