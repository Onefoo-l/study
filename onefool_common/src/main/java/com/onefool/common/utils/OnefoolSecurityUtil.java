package com.onefool.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.onefool.common.domain.vo.LoginUserVo;
import com.onefool.common.exception.CustomizeException;
import com.onefool.common.pojo.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author linjiawei
 * @Date 2023/12/26 20:09
 * @Description 获取当前用户信息的security工具类
 */
public class OnefoolSecurityUtil {

    /**
     * 获取当前用户认证信息
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static LoginUserVo getUser(){
        //principal == zs
        return (LoginUserVo) getAuthentication().getPrincipal();
    }

    /**
     * 获取当前用户id
     * @return
     */
    public static Long getUserId(){
        Long id = getUser().getId();
        if (ObjectUtil.isNull(id)) throw new CustomizeException(StatusCode.LICENSE_EXPIRED.code(),"当前用户id为空!!");
        return id;

    }
    public static String getUsername(){
        String username = getUser().getUsername();
        if (StringUtils.isEmpty(username)) throw new CustomizeException(StatusCode.LICENSE_EXPIRED.code(),"当前用户名为空!!");
        return username;
    }
}
