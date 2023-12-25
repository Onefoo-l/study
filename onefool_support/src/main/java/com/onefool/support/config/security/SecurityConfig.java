package com.onefool.support.config.security;

import com.onefool.common.exception.CustomizeException;
import com.onefool.common.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author Onefool
 * @Date 2023/12/26 2:55
 * @Description SpringSecurity安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

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
                    .cors(Customizer.withDefaults())
                    .authorizeHttpRequests(req -> req
                            .requestMatchers("/login").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults());

            return http.build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(){
//        var pro = new DaoAuthenticationProvider();
//        pro.setUserDetailsService();
//        pro.setPasswordEncoder(this.passwordEncoder());
//        return new ProviderManager(pro);
//    }

    /**
     * 配置密码加密器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
