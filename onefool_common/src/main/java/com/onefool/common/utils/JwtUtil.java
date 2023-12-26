package com.onefool.common.utils;

import cn.hutool.core.util.IdUtil;
import com.onefool.common.domain.vo.LoginUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
/**
 * @Author linjiawei
 * @Date 2023/12/26 7:14
 * @Description jwt工具类
 */
@Component
public class JwtUtil {

    private static final String secret = "qwrwqafasdgsdfgadfxxxx";

    /**
     * 生成token
     * @param loginUserVo
     * @return
     */
    public static String createToken(LoginUserVo loginUserVo){
        String token = IdUtil.simpleUUID();
        loginUserVo.setToken(token);
        var map = new HashMap<String, Object>();
        map.put("token",token);
        //TODO 存储到redis中
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
    public static Claims parseToken(String token){
        //解析token
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
