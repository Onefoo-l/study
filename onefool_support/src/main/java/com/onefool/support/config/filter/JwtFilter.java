package com.onefool.support.config.filter;

import cn.hutool.core.util.ObjectUtil;
import com.onefool.common.domain.vo.LoginUserVo;
import com.onefool.common.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author linjiawei
 * @Date 2023/12/26 5:58
 * @Description 该过滤用于验证token,每次请求只会执行一次
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("进入doFilterInternal====>");
        LoginUserVo loginUserVo = (LoginUserVo) jwtUtil.getToken(request);
        if (ObjectUtil.isNotNull(loginUserVo)){
            var userPassToken = new UsernamePasswordAuthenticationToken(loginUserVo.getUsername(),
                    null,
                    loginUserVo.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(userPassToken);
        }
        LOGGER.info("放行jwt过滤器=========>");
        filterChain.doFilter(request,response);
    }
}
