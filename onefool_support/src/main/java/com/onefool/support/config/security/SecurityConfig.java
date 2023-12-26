package com.onefool.support.config.security;

import com.onefool.common.service.impl.LoginUserVoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * @Author Onefool
 * @Date 2023/12/26 2:55
 * @Description SpringSecurity安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private LoginUserVoDetails loginUserVoDetails;

    /**
     * 配置过滤器链
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(req -> req
                            .requestMatchers("/**").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults());

            return http.build();
    }

    /**
     * 配置认证管理器
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(){
        var pro = new DaoAuthenticationProvider();
        pro.setUserDetailsService(loginUserVoDetails);
        pro.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(pro);
    }

    /**
     * 配置密码加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置跨域，允许所有请求
     * @return
     */
    public CorsConfigurationSource corsConfigurationSource(){
        var corsConfig = new CorsConfiguration();
        //配置方法
        corsConfig.setAllowedMethods(Collections.singletonList("*"));
        //配置请求头
        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
        //配置ip
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));

        //创建 CorsConfigurationSource 对象
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfig);
        return source;
    }
}
