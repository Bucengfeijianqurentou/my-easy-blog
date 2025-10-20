package com.gb.myeasyblog.service;

import com.gb.myeasyblog.dto.UserLoginReqDTO;
import com.gb.myeasyblog.dto.UserRegisterReqDTO;
import com.gb.myeasyblog.util.Result;

public interface UserService {
    Result register(UserRegisterReqDTO userRegisterReqDTO);

    Result login(UserLoginReqDTO userLoginReqDTO);
}
