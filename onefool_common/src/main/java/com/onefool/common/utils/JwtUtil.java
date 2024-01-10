package com.onefool.common.utils;

import cn.hutool.core.util.IdUtil;
import com.onefool.common.constans.CacheConstants;
import com.onefool.common.domain.vo.LoginUserVo;
import com.onefool.common.exception.CustomizeException;
import com.onefool.common.pojo.StatusCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author linjiawei
 * @Date 2023/12/26 7:14
 * @Description jwt工具类
 */
@Component
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    private static final String secret = "qwrwqafasdgsdfgadfxxxx";

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 生成token
     *
     * @param loginUserVo
     * @return
     */
    public String createToken(LoginUserVo loginUserVo) {
        String token = IdUtil.simpleUUID();
        loginUserVo.setToken(token);
        loginUserVo.setTime(System.currentTimeMillis());
        var map = new HashMap<String, Object>();
        map.put("token", token);
        //缓存到redis中
        refreshToken(loginUserVo);
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        //解析token
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public Object getToken(HttpServletRequest request) {
        String onefoolToken = request.getHeader("Onefool-Authorization");
        LOGGER.info("进入getToken方法，从请求里面获取onefoolToken值===>{}",onefoolToken);
        if (StringUtils.isEmpty(onefoolToken)) return null;
        Claims claims = parseToken(onefoolToken);
        String token = (String) claims.get("token");
        LoginUserVo loginUserVo = redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + token);
        //获取登录时间
        Long time = loginUserVo.getTime();
        long currentTime = System.currentTimeMillis();
        //是否相差20分钟
        long mills = currentTime / 1000 / 60 - time / 1000 / 60;
        if (mills >= 20) {
            refreshToken(loginUserVo);
        }
        return loginUserVo;
    }

    /**
     * 刷新token
     *
     * @param loginUserVo
     */
    public void refreshToken(LoginUserVo loginUserVo) {
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + loginUserVo.getToken(),
                loginUserVo,
                30,
                TimeUnit.MINUTES);

    }
}
