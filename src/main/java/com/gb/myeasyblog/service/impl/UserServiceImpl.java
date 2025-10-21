package com.gb.myeasyblog.service.impl;

import com.gb.myeasyblog.dto.UserLoginReqDTO;
import com.gb.myeasyblog.dto.UserRegisterReqDTO;
import com.gb.myeasyblog.entity.User;
import com.gb.myeasyblog.exception.BusinessException;
import com.gb.myeasyblog.mapper.UserMapper;
import com.gb.myeasyblog.service.UserService;
import com.gb.myeasyblog.util.HttpStatusConstants;
import com.gb.myeasyblog.util.JwtUtil;
import com.gb.myeasyblog.util.Md5Utils;
import com.gb.myeasyblog.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final JwtUtil jwtUtil;

    /**
     * 用户注册功能实现
     *
     * @param userRegisterReqDTO 用户注册请求数据传输对象，包含用户注册所需的基本信息
     * @return Result 注册结果，成功返回成功信息，失败返回错误信息
     */
    @Override
    public Result register(UserRegisterReqDTO userRegisterReqDTO) {

        //判断用户名不能重复
        if (Objects.nonNull(userMapper.selectByUsername(userRegisterReqDTO.getUsername()))) {
            throw new BusinessException(HttpStatusConstants.INTERNAL_ERROR,"用户名已存在");
        }

        // 创建用户实体对象并复制基础属性
        User user = new User();
        BeanUtils.copyProperties(userRegisterReqDTO,user);

        // 设置用户创建时间和加密后的密码
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(Md5Utils.md5(userRegisterReqDTO.getPassword()));

        // 执行用户插入操作
        int insert = userMapper.insert(user);
        if (insert != 1) {
            throw new BusinessException(HttpStatusConstants.INTERNAL_ERROR,"注册失败");
        }
        return Result.success("注册成功");
    }


    /**
     * 用户登录功能
     *
     * @param userLoginReqDTO 用户登录请求数据传输对象，包含用户名和密码
     * @return 登录结果，成功时返回token，失败时返回相应错误信息
     */
    @Override
    public Result login(UserLoginReqDTO userLoginReqDTO) {

        // 根据用户名查询用户信息
        User user = userMapper.selectByUsername(userLoginReqDTO.getUsername());

        if (Objects.isNull(user)) {
            throw new BusinessException(HttpStatusConstants.INTERNAL_ERROR,"用户不存在");
        }

        // 对输入密码进行MD5加密并与数据库中存储的密码进行比对
        String encrypt = Md5Utils.md5(userLoginReqDTO.getPassword());

        if (!encrypt.equals(user.getPassword())) {
            throw new BusinessException(HttpStatusConstants.INTERNAL_ERROR,"用户名或密码错误");
        }

        // 生成JWT token并返回登录成功结果
        String token = jwtUtil.generateToken(user.getId());

        return Result.success("登录成功",token);

    }


}
