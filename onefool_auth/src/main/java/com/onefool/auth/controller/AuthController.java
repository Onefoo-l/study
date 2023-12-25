package com.onefool.auth.controller;

import com.onefool.auth.domain.dto.LoginDto;
import com.onefool.common.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author linjiawei
 * @Date 2023/12/26 2:50
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/sys")
    public Result<String> login(@RequestBody LoginDto loginDto){
         LOGGER.info("进入登录方法!!!");
        return null;
    }
}
