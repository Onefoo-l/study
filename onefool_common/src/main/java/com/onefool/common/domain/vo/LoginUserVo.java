package com.onefool.common.domain.vo;

import cn.hutool.core.util.ObjectUtil;
import com.onefool.common.domain.entry.UmsSysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @Author linjiawei
 * @Date 2023/12/26 4:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserVo implements UserDetails {

    private Long id;

    private Long time;

    private String token;
    //用户信息
    private UmsSysUser umsSysUser = new UmsSysUser();


    /**
     * 用户权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var menuList = umsSysUser.getMenuList();
        if(ObjectUtil.isNotEmpty(menuList))  return menuList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return null;
    }

    @Override
    public String getPassword() {
        return umsSysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsSysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return umsSysUser.getStatus() == 0;
    }

    @Override
    public boolean isEnabled() {
        return umsSysUser.getStatus() == 0;
    }
}
