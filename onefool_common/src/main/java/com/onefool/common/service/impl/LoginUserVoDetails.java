package com.onefool.common.service.impl;


import cn.hutool.core.collection.CollectionUtil;

import com.onefool.common.domain.entry.UmsMenu;
import com.onefool.common.domain.entry.UmsRole;
import com.onefool.common.domain.entry.UmsSysUser;
import com.onefool.common.domain.vo.LoginUserVo;
import com.onefool.common.mapper.UmsMenuMapper;
import com.onefool.common.mapper.UmsSysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author linjiawei
 * @Date 2023/12/26 4:46
 */
@Service
public class LoginUserVoDetails implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDtoServiceImpl.class);

    @Autowired
    private UmsSysUserMapper umsSysUserMapper;

    @Autowired
    private UmsMenuMapper umsMenuMapper;

    /**
     *  /auth/sys 的登录方法最终会调用这个方法来进行验证
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("进入loadUserByUsername====>,用户名为{}",username);
        //判断是否是用户名 == 0，手机号 == 1，邮箱 == 2 进行验证
        int accountType = 0;
        UmsSysUser umsSysUser = umsSysUserMapper.selectSysUserAndRole(accountType,username);
        if (Objects.isNull(umsSysUser)) throw new UsernameNotFoundException("查询的用户名为空!");
        List<UmsRole> roleList = umsSysUser.getRoleList();
        if (CollectionUtil.isNotEmpty(roleList)) {
            List<Long> collect = roleList.stream().map(UmsRole::getRoleId).toList();
            LOGGER.info("角色id=====>{}", collect);
            List<UmsMenu> menuList = umsMenuMapper.selectRoleAndMenu(collect);
            List<String> list = menuList.stream().map(UmsMenu::getPath).toList();
            LOGGER.info("权限===>{}",list);
            umsSysUser.setMenuList(list);
        }

        var loginVo = new LoginUserVo();
        loginVo.setUmsSysUser(umsSysUser);
        loginVo.setId(umsSysUser.getId());

        return loginVo;
    }
}
