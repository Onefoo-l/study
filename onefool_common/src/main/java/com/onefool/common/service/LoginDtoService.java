package com.onefool.common.service;


import com.onefool.common.domain.dto.LoginDto;
import com.onefool.common.pojo.Result;

/**
 * @Author linjiawei
 * @Date 2023/12/26 4:36
 */
public interface LoginDtoService {


    Result<String> login(LoginDto loginDto);
}
