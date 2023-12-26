package com.onefool.common.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.onefool.common.domain.dto.LoginDto;
import com.onefool.common.domain.vo.LoginUserVo;
import com.onefool.common.exception.CustomizeException;
import com.onefool.common.pojo.Result;
import com.onefool.common.pojo.StatusCode;

import com.onefool.common.service.LoginDtoService;
import com.onefool.common.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @Author linjiawei
 * @Date 2023/12/26 4:36
 */
@Service
public class LoginDtoServiceImpl implements LoginDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDtoServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result<String> login(LoginDto loginDto) {
        LOGGER.info("进入LoginDtoServiceImpl方法====>");
        var userPassword = new UsernamePasswordAuthenticationToken(loginDto.getAccount(),loginDto.getPassword());
        //调用loaduserbyusername方法
        var authenticate = authenticationManager.authenticate(userPassword);
        //获取用户信息 返回的对象是UserDetails
        var loginUserVo = (LoginUserVo) authenticate.getPrincipal();
        //根据loginUser创建token
        if (ObjectUtil.isNull(loginUserVo)) throw new CustomizeException(StatusCode.UNAUTHORIZED.code(),"认证失败!!");
        String token = jwtUtil.createToken(loginUserVo);
        LOGGER.info("token=====>{}",token);
        return Result.ok(token);

    }
}
