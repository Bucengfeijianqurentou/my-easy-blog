package com.gb.myeasyblog.controller;

import com.gb.myeasyblog.dto.UserLoginReqDTO;
import com.gb.myeasyblog.dto.UserRegisterReqDTO;
import com.gb.myeasyblog.service.UserService;
import com.gb.myeasyblog.util.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    /**
     * 用户注册接口
     * @param userRegisterReqDTO 用户注册请求数据传输对象，包含用户注册所需的信息
     * @return 注册结果，成功时返回"注册成功"的消息
     */
    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserRegisterReqDTO userRegisterReqDTO) {
        // 调用用户服务执行注册逻辑
        return userService.register(userRegisterReqDTO);
    }


    @PostMapping("/login")
    public Result login(@Valid @RequestBody UserLoginReqDTO userLoginReqDTO){
        return userService.login(userLoginReqDTO);
    }



}
