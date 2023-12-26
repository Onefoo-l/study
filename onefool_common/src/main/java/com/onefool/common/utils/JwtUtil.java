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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author linjiawei
 * @Date 2023/12/26 7:14
 * @Description jwt工具类
 */
@Component
public class JwtUtil {

    private static final String secret = "qwrwqafasdgsdfgadfxxxx";

    @Autowired
    private RedisCacheUtil redisCacheUtil;
    /**
     * 生成token
     * @param loginUserVo
     * @return
     */
    public  String createToken(LoginUserVo loginUserVo){
        String token = IdUtil.simpleUUID();
        loginUserVo.setToken(token);
        var map = new HashMap<String, Object>();
        map.put("token",token);
        //缓存到redis中
        refreshToken(loginUserVo);
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public Claims parseToken(String token){
        //解析token
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Object getToken(HttpServletRequest request) {
        String onefoolToken = request.getHeader("onefoolToken");
        if (StringUtils.isEmpty(onefoolToken)) throw new CustomizeException(StatusCode.FAILURE.code(),"请求头中获取token为空!!");
        Claims claims = parseToken(onefoolToken);
        String token = (String) claims.get("token");
        LoginUserVo loginUserVo = redisCacheUtil.getCacheObject(CacheConstants.LOGIN_USER_KEY + token);
        //获取登录时间
        Long time = loginUserVo.getTime();
        long currentTime = System.currentTimeMillis();
        //是否相差20分钟
        long mills = currentTime / 1000 / 60 - time / 1000 / 60;
        if (mills >= 20){
            refreshToken(loginUserVo);
        }
        return null;
    }

    public void refreshToken(LoginUserVo loginUserVo){
        redisCacheUtil.setCacheObject(CacheConstants.LOGIN_USER_KEY + loginUserVo.getToken(),loginUserVo,30, TimeUnit.MINUTES);

    }
}
